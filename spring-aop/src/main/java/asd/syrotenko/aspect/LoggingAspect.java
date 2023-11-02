package asd.syrotenko.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class LoggingAspect {

	public static final String LOF_FILES_PATH = "src/main/resources/";

	@Before("execution(public * asd.syrotenko.service.*.*(..))")
	public void logBefore(JoinPoint joinPoint) throws IOException {
		String logMessage =
				"Try to call " + joinPoint.getSignature().getDeclaringType() + "#" + joinPoint.getSignature().getName() + " with parameters: " + Arrays.stream(
						joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(", ")) + "\n";
		Files.write(Paths.get(LOF_FILES_PATH + defineLogFileName(joinPoint)), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

	@AfterReturning("execution(public * asd.syrotenko.service.*.*(..))")
	public void logAfterExecution(JoinPoint joinPoint) throws IOException {
		String logMessage = "Execute method " + joinPoint.getSignature().getDeclaringType() + "#" + joinPoint.getSignature().getName() + " with parameters: "
				+ Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(", ")) + "\n";
		Files.write(Paths.get(LOF_FILES_PATH + defineLogFileName(joinPoint)), logMessage.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
	}

	private String defineLogFileName(JoinPoint joinPoint) {
		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		return "log_" + LocalDate.now() + "_" + className + "#" + methodName;
	}
}
