package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.GuiForm;

public interface GuiFormService {
    public List<GuiForm> findAll();
	List<GuiForm> findByGuiType(String type);



}
