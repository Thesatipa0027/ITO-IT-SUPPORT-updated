package com.itsupport.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.repository.PriorityRepository;
import com.itsupport.service.PriorityService;

@Service
public class PriorityServiceImpl implements PriorityService {

	@Autowired
	private PriorityRepository priorityRepository;

	@Override
	public boolean isPresent(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription(int id) {
		String description = null;
		try {
			description = priorityRepository.findById(id).get().getPriority_desc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return description;
	}
}
