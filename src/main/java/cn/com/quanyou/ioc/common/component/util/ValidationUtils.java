package cn.com.quanyou.ioc.common.component.util;

import cn.com.quanyou.ioc.common.component.exception.ArgumentNotValidException;
import cn.com.quanyou.ioc.common.component.util.utilBean.ValidationResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 请求实体验证工具类
 */
public class ValidationUtils {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> ValidationResult getValidateInfo(T obj) {

        return getValidationResult(obj);
    }

    public static <T> void validateEntity(T obj) throws ArgumentNotValidException {
        ValidationResult validationResult = getValidationResult(obj);

        //判断result不为空时，直接抛出异常
        if (validationResult.isHasErrors()){
            Map<String, String> errorMap = validationResult.getErrorMsg();
            if(!errorMap.isEmpty()){
                StringBuffer sb = new StringBuffer();
                for (Map.Entry<String, String> m : errorMap.entrySet()) {
                    sb.append(m.getValue()).append("\r\n");
                }
                throw new ArgumentNotValidException(sb.toString());
            }
        }
    }

    private static <T> ValidationResult getValidationResult(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() != 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }

    public static <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (set != null && set.size() != 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }
}
