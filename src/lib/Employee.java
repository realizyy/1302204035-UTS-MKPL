package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */


	/**
	 	Duplicate code pada fungsi setMonthlySalary(), yaitu jika Foreigner(WNA)
	 	maka gaji diperbesar sebanyak 50% pada setiap grade.
	 	Perubahan :
	 		1. Menghapus kondisi if(isForeigner) pada setiap grade
	 		2. Menambahkan kondisi if(isForeigner) pada setMonthlySalary()
	 **/
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 3000000;
		}else if (grade == 2) {
			monthlySalary = 5000000;
		}else if (grade == 3) {
			monthlySalary = 7000000;
		}

		//kondisi ketika pegawai adalah warga negara asing
		if (isForeigner) {
			monthlySalary = (int) (3000000 * 1.5);
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	/**
	 Long Method pada fungsi getAnnualIncomeTax(), yaitu terdapat banyak perhitungan pada fungsi ini.
	 perhitungan tersebut dapat dibuatkan fungsi baru untuk mengurangi kompleksitas pada fungsi ini.
	 Perubahan :
	 	1. Membuat fungsi calculateWorkingMonth() untuk menghitung bulan bekerja pada tahun ini
	 	2. Membuat fungsi isMarried() untuk mengecek apakah pegawai sudah menikah
	 	3. Membuat fungsi getChildCount() untuk menghitung jumlah anak
	 **/

	//fungsi untuk menghitung bulan bekerja pada tahun ini
	private int calculateWorkingMonth() {
		LocalDate date = LocalDate.now();
		if (date.getYear() == yearJoined) {
			return date.getMonthValue() - monthJoined;
		}else {
			return 12;
		}
	}

	//fungsi untuk mengecek apakah pegawai sudah menikah
	private boolean isMarried() {
		// mereturn true jika sudah menikah, false jika belum menikah
		return !spouseIdNumber.equals("");
	}

	private int getChildCount() {
		// mereturn jumlah anak
		return childIdNumbers.size();
	}


	//fungsi untuk menghitung pajak tahunan
	public int getAnnualIncomeTax() {
		int monthWorkingInYear = calculateWorkingMonth();
		// mereturn pajak tahunan pegawai
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, isMarried(), getChildCount());
	}
}
