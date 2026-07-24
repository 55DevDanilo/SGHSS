package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.PatientDao;
import model.dao.service.PatientService;
import model.entities.Patient;

public class Principal {

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		Scanner sc = new Scanner(System.in);
		PatientDao patientDao = DaoFactory.createPatientDao();
		PatientService patientService = new PatientService(patientDao);

		// program e testar as validações***
		char decisão = 's';

		System.out.println("\n=== TESTE 1 : Criação do Paciente ===");
		while (decisão != 'n') {
			System.out.println("\n=== Qual é o nome do paciente ? ===");
			String nome = sc.nextLine();
			System.out.println("\n=== Qual é o email do paciente ? ===");
			String email = sc.nextLine();
			System.out.println("\n=== Qual é o telefone do paciente ? ===");
			String telefone = sc.nextLine();
			System.out.print("Data de nascimento (dd-MM-yyyy): ");
			String dataNascimento = sc.nextLine();
			LocalDate dateNascimento = LocalDate.parse(dataNascimento, formatter);
			Patient patient = new Patient(nome, email, telefone, dateNascimento);
			patientService.createPatient(patient);
			System.out.println("Paciente cadastrado com sucesso!");

			System.out.println("Gostaria de cadastrar mais um paciente ? [s/n]");
			decisão = sc.next().toLowerCase().charAt(0);
			sc.nextLine(); 

		}
		sc.close();

	}

}
