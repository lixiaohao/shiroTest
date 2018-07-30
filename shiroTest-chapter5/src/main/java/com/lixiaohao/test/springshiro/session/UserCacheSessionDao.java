package com.lixiaohao.test.springshiro.session;

import com.lixiaohao.test.springshiro.dao.SessionModelDao;
import com.lixiaohao.test.springshiro.dao.UserDao;
import com.lixiaohao.test.springshiro.model.SessionModel;
import com.lixiaohao.test.springshiro.model.User;
import com.lixiaohao.test.springshiro.util.JsonUtils;
import com.lixiaohao.test.springshiro.util.SerializeUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import com.lixiaohao.test.springshiro.util.StringUtils;

/**
 * @program: shiroTest
 * @description:
 * @author: xiaohao.li
 * @create: 2018-07-26 12:40
 **/

public class UserCacheSessionDao extends CachingSessionDAO{

    private final String PRINCIPALS_SESSION_KEY = "org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";

    @Autowired
    private SessionModelDao sessionModelDao;

    @Autowired
    private UserDao userDao;

    protected void doUpdate(Session session) {
        if ( session == null ) {
            throw new NullPointerException("session argument can not be null");
        }

        String sessionId = (String)session.getId();

        if ( StringUtils.isEmpty(sessionId) ) {
            throw new NullPointerException(" property sessionId can not be null  in session argument ");
        }

        SessionModel sessionModel = sessionModelDao.findBySessionId(sessionId);
        if ( sessionModel == null ) {
            throw new NullPointerException(" please login  ");
        }

        String sessionStr = SerializeUtils.serializeToString((SimpleSession)session);
        sessionModel.setSessionStr(sessionStr);
        sessionModelDao.update(sessionModel);

    }

    protected void doDelete(Session session) {
        if ( session == null ) {
            throw new NullPointerException(" session argument can not be null ");
        }

        String sessionId = (String)session.getId();

        if ( StringUtils.isEmpty(sessionId) ) {
            throw new NullPointerException(" property sessionId can not be null  in session argument ");
        }

        sessionModelDao.delete(sessionId);

    }

    protected Serializable doCreate(Session session) {

        if ( session == null ) {
            throw new NullPointerException(" session argument can not be null ");
        }
        String sessionId = (String) generateSessionId(session);

        assignSessionId(session,sessionId);

        storeSession(session);

        return sessionId;
    }

    private void storeSession(Session session){
        String sessionId = (String) session.getId();
        SessionModel sessionModel = new SessionModel();
        sessionModel.setSessionId(sessionId);
        sessionModel.setStatus(1);
        String sessionStr = SerializeUtils.serializeToString((SimpleSession)session);
        sessionModel.setSessionStr(sessionStr);

        Object principals = session.getAttribute(PRINCIPALS_SESSION_KEY);
        if ( principals != null ) {
            User user = userDao.findByUserName((String) principals);
            sessionModel.setValue(JsonUtils.objectToJson(user));
        }

        System.out.println("保存session -------->> "+JsonUtils.objectToJson(sessionModel));

        sessionModelDao.insert(sessionModel);

    }

    protected Session doReadSession(Serializable sessionId) {
        if ( sessionId == null ) {
            throw new NullPointerException(" sessionId argument can not be null. ");
        }
        String id = (String) sessionId;
        SessionModel sessionMode = sessionModelDao.findBySessionId(id);
        if ( sessionMode == null ) {
            return null;
        }
        java.lang.String sessionStr = sessionMode.getSessionStr();
        Session session = SerializeUtils.deserializeFromString(sessionStr);
        return session;
    }


}
