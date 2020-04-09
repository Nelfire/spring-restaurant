package dev.ihm.options;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

import dev.exception.PlatException;

@Order(3)
@Controller
public class OptionTerminer implements IOptionMenu {
	@Override
	public String getTitre() {
		return "Terminer";
	}

	@Override
	public void executer() {
		throw new PlatException("Aurevoir");
	}
}
