package com.diego.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.diego.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//?0 recebe o primeiro parametro que vier no metodo, no caso o text
	@Query("{ 'title': { $regex: ?0 , $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query(" { $and: [ {date: {$gte: ?1} }, { date: {$lte: ?2} }, { $or: [ {'title': { $regex: ?0, $options: 'i'} } , {'body': {$regex: ?0, $options: 'i' }}, {'comments.text': {$regex: ?0 , $options: 'i' }} ] } ] } ")
	List<Post> fullSearch(String text, Date minData, Date maxData);
	
}
