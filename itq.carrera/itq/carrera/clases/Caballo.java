package itq.carrera.clases;

import java.awt.*;
import javax.swing.*;

import itq.carrera.frames.FramePrincipal;

@SuppressWarnings("serial")
// El objeto de caballo extiende a la clase JLabel ya que 
// es necesario modificar su posición y tamaño, así como
// asignarle un ícono
//
// También implementa la interfaz runnable ya que cada "caballo" va asociado a un hilo
// que controla su movimiento
public class Caballo extends JLabel implements Runnable {

	// Cada caballo tendrá su propio hilo, y el número de caballo
	Thread hilo;
	int numeroCaballo;
	
	// Se inicializa el caballo con la ruta del ícono que tendrá, y el número de caballo
	public Caballo(String rutaImagen, int numeroCaballo)
	{
		// Establecemos la imagen del caballo, de 100 x 100
		this.setIcon(new ImageIcon(this.getToolkit().getImage(rutaImagen).getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		this.numeroCaballo = numeroCaballo;
		// Aseguramos que se vuelva a dibujar el caballo con el ícono
		this.repaint();
	} 
	
	public void run()
	{

		// Las coordenadas que pertenezcan a x = 800 se consideran la "meta"
		// cuando un caballo llega, gana la carrera
		while(this.getX() <= 800) 
		{
			try 
			{
				// El hilo hace que el caballo avance cada 200ms una cantidad Y aleatoria entre 20 y 49 pixeles
				// Es necesario tomar en cuenta que no todos los caballos están a la misma altura y,
				// pero como son equidistantes solo hace falta multiplicar la distancia entre ellos por el número
				// del caballo
				this.setBounds(this.getX()+(int)(20+Math.random()*50), 120*(numeroCaballo), 100, 100);
				Thread.currentThread();
				Thread.sleep(200);
				
				// Nos aseguramos de mostrar la nueva posición del caballo en el Frame
				this.repaint();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		// Si ningún caballo ha llegado aún y ya se superó la coordenada x = 800,
		// entonces se anuncia el caballo ganador y se indica que ya llegó un caballo
		// para evitar múltiples "ganadores"
		if(!FramePrincipal.llegoMeta && this.getX()>800)
		{
			anunciarGanador(numeroCaballo);
			// Volvemos a habilitar el botón para iniciar una carrera cuando algún caballo gana
			FramePrincipal.btnInicio.setEnabled(true);
		}
		
		// Si algún caballo avanzo mas de 800 pixeles, lo regresamos a la meta (meramente estético)
		if(this.getX()>800)
		{
			this.setBounds(800, this.getY(), 100, 100);	
		}
		
	}
	
	public void anunciarGanador(int caballoGanador)
	{
		FramePrincipal.llegoMeta = true;
		// Anunciamos el caballo que fue el primero en llegar
		System.out.println("Ganó el caballo "+caballoGanador);
		
		// Veriricamos si el caballo que ganó fue por el que el usuario puso la apuesta, y mostramos
		// un mensaje en pantalla adecuado
		if(caballoGanador==FramePrincipal.apuesta)
		{
			JOptionPane.showMessageDialog(this, "Ha ganado el caballo "+caballoGanador+"!"
					+"\nLe has atinado al caballo que ganó!");
		}
		else 
		{
			JOptionPane.showMessageDialog(this, "Ha ganado el caballo "+caballoGanador+"!"
					+"\nGanó otro caballo, has perdido tu apuesta...");
		}
	}
}
