package itq.carrera.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import itq.carrera.clases.*;

@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {

	
	public static Caballo[] caballos = new Caballo[4];
	JPanel panelCaballos = (JPanel) this.getContentPane();
	JPanel panelApuesta;
	// Creamos un label para mostrar información
	JLabel lblTitulo;
	public static JButton btnInicio;
	Choice lstApuestas;
	// Creamos esta variable para guardar la apuesta
	public static int apuesta;
	public static boolean llegoMeta;
	
	public FramePrincipal()
	{
		// Sin administrador de diseño
		this.setLayout(null);
		// Cambiamos el título de la ventana
		this.setTitle("Carrera de caballos");
		
		// Creamos un label para el estado
		Font fuenteLabel = new Font("Monospace", Font.BOLD, 50);
		lblTitulo = new JLabel("Carrera de Caballos");
		lblTitulo.setFont(fuenteLabel);
		lblTitulo.setBounds(275, 30, 500, 100);
		panelCaballos.add(lblTitulo);
		
		// Inicializamos lista
		lstApuestas = new Choice();
		
		// Creamos los elementos del arreglo que contendrá los caballos
		for(int i = 0; i < caballos.length; i++)
		{
			// Inicializamos objetos en el arreglo
			caballos[i] = new Caballo("res/caballo.png", i+1);
			
			// Indicamos tamaño y posición de cada objeto
			caballos[i].setBounds(30, (i+1)*120, 100, 100);
			
			// Modificamos el resto de propiedades y añadimos al panel
			caballos[i].setText("Caballo "+(1+i));
			// También añadimos la opción a la lista de apuestas
			lstApuestas.add("Caballo "+(1+i));
			caballos[i].setBackground(Color.black);
			panelCaballos.add(caballos[i]);
		}
		
		// Creamos el evento para responder al botón de inicio
		btnInicio = new JButton("Iniciar carrera");
		btnInicio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Inicialiamos los labels e hilos que están en la carrera
				for(int i = 0; i < caballos.length; i++)
				{
					caballos[i].setBounds(30, (i+1)*120, 100, 100);
					// Como Caballo implementa la interfaz runnable, puede usarse
					// para crear un hilo
					new Thread(caballos[i]).start();
				}
				
				// Verificamos cual de toos los caballos fue al que apostó el usuario
				if(lstApuestas.getItem(lstApuestas.getSelectedIndex()).equals("Caballo 1"))
				{
					apuesta = 1;
				}
				else if(lstApuestas.getItem(lstApuestas.getSelectedIndex()).equals("Caballo 2"))
				{
					apuesta = 2;
				}
				else if(lstApuestas.getItem(lstApuestas.getSelectedIndex()).equals("Caballo 3"))
				{
					apuesta = 3;
				}
				else if(lstApuestas.getItem(lstApuestas.getSelectedIndex()).equals("Caballo 4"))
				{
					apuesta = 4;
				}
				
				// Cada que se apreta el botón, se inicia una carrera nueva y se desactiva el botón
				// para iniciar carrera hasta que haya otro ganador
				llegoMeta = false;
				btnInicio.setEnabled(false);
				
			}
			
		});
		
		// Añadimos la lista y el botón al panel donde irá
		panelApuesta = new JPanel();
		panelApuesta.setLayout(new FlowLayout());
		panelApuesta.add(lstApuestas);
		panelApuesta.add(btnInicio);
		
		// Establecemos el panel de apuesta para que se muestre hasta abajo del todo
		panelApuesta.setBounds(0, 600, 1000, 100);
		panelCaballos.add(panelApuesta);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}
