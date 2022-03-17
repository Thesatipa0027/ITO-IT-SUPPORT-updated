package com.itsupport.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.repository.CategoryRepository;
import com.itsupport.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean isPresent(int categoryId) {
		boolean isPresent = false;
		try {
			isPresent = categoryRepository.findById(categoryId).isPresent();
		} catch (Exception e) {

		}
		return isPresent;
	}

	@Override
	public String getDescription(int categoryId) {
		String description = null;
		try {
			description = categoryRepository.findById(categoryId).get().getCategory_desc();
		} catch (Exception e) {

		}
		return description;
	}

}
