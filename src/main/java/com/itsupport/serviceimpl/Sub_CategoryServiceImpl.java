package com.itsupport.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsupport.repository.SubCategoryRepository;
import com.itsupport.service.Sub_CategoryService;

@Service
public class Sub_CategoryServiceImpl implements Sub_CategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Override
	public boolean isPresent(int s_id, int c_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription(int s_id, int c_id) {
		String description = null;
		try {
			if (subCategoryRepository.findById(s_id).get().getCategory().getCategory_id() == c_id)
				description = subCategoryRepository.findById(s_id).get().getSub_category_desc();

		} catch (Exception e) {

		}
		return description;
	}

}
