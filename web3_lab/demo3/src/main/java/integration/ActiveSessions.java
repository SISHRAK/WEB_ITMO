package integration;

import data.ActiveSession;
import data.UserRequest;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ActiveSessions implements Serializable {
    private EntityManager em;

    private Long sessionKey;

    private String getSessionId() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        return session.getId();
    }

    @PostConstruct
    public void init(){
        em = Manager.generateEntityManager();

        ActiveSession currentSession = new ActiveSession();
        currentSession.setSessionID(getSessionId());
        em.persist(currentSession);
        sessionKey = currentSession.getId();
    }

    public List<UserRequest> getRequests() {
        ActiveSession session = em.find(ActiveSession.class, sessionKey);
        return session.getRequests();
    }

    public void saveRequest(UserRequest request) {
        em.getTransaction().begin();
        ActiveSession currentSession = em.find(ActiveSession.class, sessionKey);
        currentSession.addRequest(request);
        em.getTransaction().commit();
    }

    @PreDestroy
    public void removeActive() {
        em.getTransaction().begin();
        ActiveSession currentSession = em.find(ActiveSession.class, sessionKey);
        if (currentSession != null) em.remove(currentSession);
        em.getTransaction().commit();
    }
}