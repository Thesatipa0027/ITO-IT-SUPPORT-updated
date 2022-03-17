package com.itsupport.service;

public interface Sub_CategoryService {
	boolean isPresent(int s_id, int c_id);

	String getDescription(int s_id, int c_id);
}
