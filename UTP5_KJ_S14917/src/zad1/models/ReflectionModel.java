package zad1.models;

import java.lang.reflect.Field;
import java.util.List;

public class ReflectionModel {
    private Model1 model1;
    private List<Integer> years;
    private List<RateModel> rateModels;

    public ReflectionModel(Model1 model1, List<Integer> years, List<RateModel> rateModels) throws Exception {
        this.model1 = model1;
        this.years = years;
        this.rateModels = rateModels;
    }

    public void addValues() throws Exception {
        Class<? extends Model1> modelClass = model1.getClass();
        Field yearsField = modelClass.getDeclaredField("LL");
        yearsField.setAccessible(true);

        if (yearsField.getAnnotation(Bind.class) != null) {
            yearsField.setInt(model1, years.size());
        }
    	rateModels.forEach(rateModel -> {
            try {
                Class<? extends Model1> mclass = model1.getClass();
                Field years;
        		try {
        			years = mclass.getDeclaredField(rateModel.getLabel());
        	        years.setAccessible(true);

        	        if (years.getAnnotation(Bind.class) != null) {
        	            years.set(model1, rateModel.getValues());
        	        }
        		} catch (NoSuchFieldException e) {
        			e.printStackTrace();
        		}
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public double[] getArrayFromModel(String fieldName) throws Exception {
        Class<? extends Model1> modelClass = model1.getClass();
        Field pkbField = modelClass.getDeclaredField(fieldName);
        pkbField.setAccessible(true);

        if (pkbField.getAnnotation(Bind.class) == null) {
            throw new Exception("Exception");
        }
        return (double[]) pkbField.get(model1);
    }


}
