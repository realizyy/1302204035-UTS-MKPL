package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */


	/**
	 	Long Method pada fungssi calculateTax(), yaitu terdapat banyak perhitungan pada fungsi ini dan terlalu
	 	banyak parameter yang harus diinputkan.
	 	Perubahan :
	 	1. Menambahkan variabel static untuk menampung nilai-nilai yang sering digunakan
	 	2. Merubah fungsi calculateTax() menjadi lebih sederhana
	 **/

	private static final double TAX_RATE = 0.05;
	private static final int MAX_MONTH_OF_WORKING = 12;
	private static final int DEDUCTIBLE = 0;
	private static final int NON_TAXABLE_INCOME_SINGLE = 54000000;
	private static final int NON_TAXABLE_INCOME_MARRIED = 58500000;
	private static final int NON_TAXABLE_INCOME_CHILD = 1500000;
	private static final int MAX_CHILD = 3;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		int tax = 0;

		// mengecek apakah jumlah bulan bekerja lebih dari 12
		if (numberOfMonthWorking > MAX_MONTH_OF_WORKING) {
			System.err.println("Number of month working is more than 12");
		}

		// mengecek apakah jumlah anak lebih dari 3
		if (numberOfChildren > MAX_CHILD) {
			numberOfChildren = MAX_CHILD;
		}

		// menghitung pajak	pegawai
		int nonTaxableIncome = isMarried ? NON_TAXABLE_INCOME_MARRIED : NON_TAXABLE_INCOME_SINGLE;

		if (numberOfChildren > 0) {
			nonTaxableIncome += numberOfChildren * NON_TAXABLE_INCOME_CHILD;
		}

		tax = (int) Math.round(TAX_RATE * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - nonTaxableIncome));

		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}

	}
}
