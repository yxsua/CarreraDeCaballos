package itq.carrera.main;

import itq.carrera.frames.*;

public class Principal {

	public static void main(String[] args)
	{
		// Creamos un frame para mostrar la carrera de caballos
		FramePrincipal framePrincipal = new FramePrincipal();
		framePrincipal.setBounds(300, 50, 1000, 700);
		framePrincipal.setResizable(false);
		framePrincipal.setVisible(true);
	}
}
