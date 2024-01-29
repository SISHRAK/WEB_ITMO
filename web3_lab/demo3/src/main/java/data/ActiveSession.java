package data;

import jakarta.persistence.*;


import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "active_sessions")
public class ActiveSession {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "active_session_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "session_id", unique = true)
    private String sessionID;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "session_id", nullable = false)
    private List<UserRequest> requests = new LinkedList<>();

    public Long getId() {
        return id;
    }



    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public List<UserRequest> getRequests() {
        return requests;
    }

    public void addRequest(UserRequest request) {
        requests.add(0, request);
    }
}