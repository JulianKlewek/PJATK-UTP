package zad1.models;



import java.util.Arrays;
import java.util.List;

public class TsvResult {
	private List<Integer> years;
	private List<RateModel> rateModels;
	private List<Double> pkb;

	public TsvResult(List<Integer> years, List<RateModel> rateModels, List<Double> pkb, List<Double> zdeks,
			String lineText) {
		this.years = years;
		this.rateModels = rateModels;
		this.pkb = pkb;
	}

	public String generateTsvResult() {
		StringBuilder sb = new StringBuilder();

		if (years != null) {
			sb.append("LATA");
			years.forEach(year -> sb.append("\t").append(year));
			sb.append("\n");
		}
		rateModels.forEach(rateModel -> {
			sb.append(rateModel.getLabel());
			Arrays.stream(rateModel.getValues()).forEach(value -> sb.append("\t").append(value));
			sb.append("\n");
		});
		if (pkb != null) {
			sb.append("PKB");
			pkb.forEach(year -> sb.append("\t").append(year));
			sb.append("\n");
		}
		return sb.toString();
	}

}
