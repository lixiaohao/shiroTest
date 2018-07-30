package com.lixiaohao.test.springshiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @program: shiroTest
 * @description:
 * @author: xiaohao.li
 * @create: 2018-07-26 12:40
 **/

public class UserMemorySessionDao extends MemorySessionDAO {

    private final String PRINCIPALS_SESSION_KEY = "org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";

    private static final Logger log = LoggerFactory.getLogger(MemorySessionDAO.class);

    private ConcurrentMap<Serializable, Session> sessions;

    public UserMemorySessionDao() {
        this.sessions = new ConcurrentHashMap<Serializable, Session>();
    }

    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        storeSession(sessionId, session);
        return sessionId;
    }

    protected Session storeSession(Serializable id, Session session) {
        if (id == null) {
            throw new NullPointerException("id argument cannot be null.");
        }
        return sessions.putIfAbsent(id, session);
    }

    protected Session doReadSession(Serializable sessionId) {
        return sessions.get(sessionId);
    }

    public void update(Session session) throws UnknownSessionException {

        Object attribute = session.getAttribute(PRINCIPALS_SESSION_KEY);
        if ( attribute instanceof PrincipalCollection) {
            PrincipalCollection principalCollection = (PrincipalCollection)attribute;
            String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
            Set<String> realmNames = principalCollection.getRealmNames();
        }
        storeSession(session.getId(), session);
    }

    public void delete(Session session) {
        if (session == null) {
            throw new NullPointerException("session argument cannot be null.");
        }
        Serializable id = session.getId();
        if (id != null) {
            sessions.remove(id);
        }
    }

    public Collection<Session> getActiveSessions() {
        Collection<Session> values = sessions.values();
        if (CollectionUtils.isEmpty(values)) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableCollection(values);
        }
    }

}
