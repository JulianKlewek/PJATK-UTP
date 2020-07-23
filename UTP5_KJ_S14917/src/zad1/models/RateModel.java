package zad1.models;



public class RateModel {

    private String label;
    private double[] values;

    public RateModel(String label, double[] values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public double[] getValues() {
        return values;
    }

	public void setLabel(String label) {
		this.label = label;
	}

	public void setValues(double[] values) {
		this.values = values;
	}
}
