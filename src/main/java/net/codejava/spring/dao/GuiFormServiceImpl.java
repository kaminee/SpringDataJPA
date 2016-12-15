package net.codejava.spring.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.codejava.spring.model.GuiForm;
import net.codejava.spring.repository.GUIFormRepository;

@Service
public class GuiFormServiceImpl implements GuiFormService{

	@Resource
    private GUIFormRepository guiFormRepository;

	@Override
	public List<GuiForm> findAll() {
		System.out.println("\n\t guiFormRepository"+guiFormRepository);
        return guiFormRepository.findAll();
	}

	@Override
	public List<GuiForm> findByGuiType(String type) {
        return guiFormRepository.findByGuiType(type);

	}
    

}
