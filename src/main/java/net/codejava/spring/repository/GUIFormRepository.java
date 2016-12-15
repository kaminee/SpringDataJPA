package net.codejava.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.spring.model.GuiForm;

public interface GUIFormRepository extends JpaRepository<GuiForm, String>{
	List<GuiForm> findByGuiType(String type);
	List<GuiForm> findAll();

}
