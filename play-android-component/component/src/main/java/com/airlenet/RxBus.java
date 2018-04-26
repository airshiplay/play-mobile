package com.airlenet;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * <pre>
 *    1\发送事件的代码
 *     RxBus.getDefault().post(new UserEvent (1, "yoyo"));
 *2\接收事件的代码
 * rxSubscription是一个Subscription的全局变量，这段代码可以在onCreate/onStart等生命周期内
 rxSubscription = RxBus.getDefault().toObserverable(UserEvent.class)
 .subscribe(new Action1<UserEvent>() {

public void call(UserEvent userEvent) {
long id = userEvent.getId();
String name = userEvent.getName();
...
}
},
 new Action1<Throwable>() {
public void call(Throwable throwable) {
// TODO: 处理异常
}
});
3\取消订阅事件

 protected void onDestroy() {
 super.onDestroy();
 if(!rxSubscription.isUnsubscribed()) {
 rxSubscription.unsubscribe();
 }
 }
 * </pre>
 * Created by lig on 16/6/27.
 */
public class RxBus {
    private static volatile RxBus defaultInstance;
    // 主题
    private final Subject bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    // 单例RxBus
    public static RxBus getDefault() {
        RxBus rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBus();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }
    // 提供了一个新的事件
    public void post (Object o) {
        bus.onNext(o);
    }
    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObserverable (Class<T> eventType) {
        return bus.ofType(eventType);
//        这里感谢小鄧子的提醒: ofType = filter + cast
//        return bus.filter(new Func1<Object, Boolean>() {
//            @Override
//            public Boolean call(Object o) {
//                return eventType.isInstance(o);
//            }
//        }) .cast(eventType);
    }
}
