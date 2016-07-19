package com.luxoft.wheretogo.services;

import com.luxoft.wheretogo.models.User;
import com.luxoft.wheretogo.models.UserInfo;
import org.hibernate.Hibernate;

import java.util.List;

public interface UsersService {

	void add(UserInfo user);

	List<User> findAll();

	User findById(long userId);

	User findByEmail(String userLogin);

	void update(User user);

	List<User> getNotParticipants(long groupId);

	User initGroups(User user);

	User initEvents(User user);
}
