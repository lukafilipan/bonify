package com.filipan.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.filipan.model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {

	@Query(value = "select server.* from server left join user_server on user_server.server_id = server.id where user_server.user_id = ?1", 
			nativeQuery = true)
	Collection<Server> findAllSubscribed(Long id);
	
	@Query(value = "select * from server where server.id not in (select server_id from user_server where user_server.user_id = ?1)", 
			nativeQuery = true)
	Collection<Server> findAllUnsubscribed(Long id);
	
}
