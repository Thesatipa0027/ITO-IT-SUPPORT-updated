package com.itsupport.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.repository.StatusRepository;
import com.itsupport.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Override
	public boolean isPresent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription(int id) {
		String description = null;
		try {
			description= statusRepository.findById(id).get().getStatus_desc();
		} catch (Exception e) {

		}
		return description;
	}

}
