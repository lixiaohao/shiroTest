package com.lixiaohao.test.springshiro.dao;

import com.lixiaohao.test.springshiro.model.SessionModel;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
public interface SessionModelDao {

    /***
     *  根据sessionId查询
     *
     * @param sessionId
     * @return
     */
    SessionModel findBySessionId(String sessionId);

    /**
     * 新增
     *
     * @param sessionModel
     */
    void insert(SessionModel sessionModel);


    /***
     * 删除
     * @param sessionModel
     */
    void delete(String sessionModel);

    /***
     * 更新session
     * @param sessionModel
     */
    void update(SessionModel sessionModel);

}
