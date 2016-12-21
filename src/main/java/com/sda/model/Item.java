package com.sda.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sda.enums.ItemType;
import com.sda.enums.State;

@Entity
@Table(name = "Item", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    private int id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "body", length = 50)
    private String body;

    @Column(name = "itemType", nullable = false)
    private ItemType type;

    @Min(1)
    @Max(5)
    @Column(name = "priority", nullable = false, length = 1)
    private int priority;

    @Min(1)
    @Max(3)
    @Column(name = "severity", nullable = false, length = 1)
    private int severity;

//    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "itemTags")
//    private List<Tag> tags = new ArrayList<Tag>();

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User assignedUser;

    @Column(name = "state", nullable = false)
    private State state;

    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "modified", nullable = false)
    private Date modified;

    @Column(name = "originalEstimate", nullable = false)
    private int originalEstimate;

    @Column(name = "remainingHours", nullable = false)
    private int remainingHours;

    @Column(name = "completedHours", nullable = false)
    private int completedHours;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public User getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(User assignedUser) {
		this.assignedUser = assignedUser;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getOriginalEstimate() {
		return originalEstimate;
	}

	public void setOriginalEstimate(int originalEstimate) {
		this.originalEstimate = originalEstimate;
	}

	public int getRemainingHours() {
		return remainingHours;
	}

	public void setRemainingHours(int remainingHours) {
		this.remainingHours = remainingHours;
	}

	public int getCompletedHours() {
		return completedHours;
	}

	public void setCompletedHours(int completedHours) {
		this.completedHours = completedHours;
	}

	@Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", body=" + body + ", type=" + type + ", priority=" + priority
                + ", severity=" + severity
//              + ", tags=" + tags 
                + ", assignedTo=" + assignedUser + ", state=" + state
                + ", created=" + created + ", modified=" + modified + ", originalEstimate=" + originalEstimate
                + ", remainingHours=" + remainingHours + ", completedHours=" + completedHours + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        return true;
    }


}

