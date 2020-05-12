package com.fundooNotes.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fundooNotes.model.Label;

@Repository
public interface LabelRepository extends CrudRepository<Label, Integer> {
Label findByLabelName(String labelName);
}
