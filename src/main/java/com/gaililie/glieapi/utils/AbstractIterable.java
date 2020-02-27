package com.gaililie.glieapi.utils;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * 自动分页工具类
 *
 * FIXME warning 不要使业务类继承，推荐用匿名局部内部类使用，用代理避免单继承问题：@SEE demo main
 *
 * @param <T>
 */
public abstract class AbstractIterable<T> implements Iterable<List<T>>{

    private final long initialLargerThanId;
    private final long batchSize;
    private final Function<T, Long> identityApplier;

    public AbstractIterable(long initialLargerThanId, long batchSize, Function<T, Long> identityApplier) {
        this.initialLargerThanId = initialLargerThanId;
        this.batchSize = batchSize;
        this.identityApplier = identityApplier;
    }


    @NotNull
    @Override
    public Iterator<List<T>> iterator() {
        return new AbstractIterator<List<T>>() {
            private long lastLargerThanId = initialLargerThanId;
            private long lastFetchSize = batchSize;
            @Override
            protected List<T> computeNext() {
                if(lastFetchSize < batchSize){
                    return endOfData();
                }
                List<T> tList = nextPage(lastLargerThanId, batchSize);
                lastFetchSize = tList.size();
                if(!CollectionUtils.isEmpty(tList)){
                    lastLargerThanId = identityApplier.apply(tList.get(tList.size() - 1));
                }
                return tList;
            }
        };
    }

    public abstract List<T> nextPage(long nextLargerThanId, long take);

    /**
     * 使用demo
     *
     * @param args
     */
    public static void main(String[] args){
        class Person{
            final long id;
            public Person(long id) {
                this.id = id;
            }
        }

        for(List<Person> personList : new AbstractIterable<Person>(0L, 100L , it -> it.id) {
            @Override
            public List<Person> nextPage(long nextLargerThanId, long take) {
                List<Person> personList = Lists.newArrayList();
                for(long start = nextLargerThanId + 1; personList.size() < take; start++){
                    // 制造停止
                    if(start == 901L) break;
                    personList.add(new Person(start));
                }
                return personList;
            }
        }){
            if(!CollectionUtils.isEmpty(personList)){
                System.out.println(personList.get(0).id + "~" + personList.get(personList.size() -1).id);
            }
        }
    }
}
