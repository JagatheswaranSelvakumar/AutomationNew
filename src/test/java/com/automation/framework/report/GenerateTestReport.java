package com.automation.framework.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automation.framework.common.CommonVariable;
import com.github.mkolisnyk.cucumber.reporting.CucumberCoverageOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberDetailedResults;
import com.github.mkolisnyk.cucumber.reporting.CucumberFeatureOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberOverviewChartsReport;
import com.github.mkolisnyk.cucumber.reporting.CucumberResultsOverview;
import com.github.mkolisnyk.cucumber.reporting.CucumberUsageReporting;
import com.github.mkolisnyk.cucumber.runner.runtime.ExtendedRuntimeOptions;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

public class GenerateTestReport {
	private static Logger logger = LogManager.getLogger(GenerateTestReport.class);

	public static void generateCucumberJVMReport() {
		try {
			logger.info("Generating Cucumber JVM Report is Started");
			File reportOutputDirectory = new File(CommonVariable.getCurrentRootDir() + "/target/");
			List<String> jsonFiles = new ArrayList<String>();
			jsonFiles.add(CommonVariable.getCurrentRootDir() + CommonVariable.getCucumberJsonReportPath());
			String buildNumber = "1";
			String projectName = "Manulife GBNowDX";
			boolean runWithJenkins = false;
			boolean parallelTesting = false;
			Configuration configuration = new Configuration(reportOutputDirectory, projectName);
			configuration.setParallelTesting(parallelTesting);
			configuration.setRunWithJenkins(runWithJenkins);
			configuration.setBuildNumber(buildNumber);
			configuration.addClassifications("BreakPoint", CommonVariable.getBreakPoint());
			configuration.addClassifications("Browser", CommonVariable.getBrowserName());
			configuration.addClassifications("Platform", CommonVariable.getPlatform());
			configuration.addClassifications("Version", CommonVariable.getVersion());
			configuration.addClassifications("Branch", "release/1.0");
			configuration.getTrendsLimit();
			configuration.getTagsToExcludeFromChart();
			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			Reportable result = reportBuilder.generateReports();
			int passedScenarios = result.getPassedScenarios();
			int failedScenarios = result.getFailedScenarios();
			int totalScenarios = result.getScenarios();
			logger.info("****************************************************************");
			logger.info("Total Scenarios::::" + totalScenarios);
			logger.info("Passed Scenarios::::" + passedScenarios);
			logger.info("Failed Scenarios::::" + failedScenarios);
			logger.info("*****************************************************************");
			result.getScenarios();
			logger.info("Generating Cucumber JVM Report is Completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber JVM report");
			logger.error(e.getMessage());
		}
	}

	public static void generateCucumberCoverageOverviewReport() throws Exception {
		try {
			logger.info("Generating cucumber coverage overview report is started");
			CucumberCoverageOverview cucumberCoverageOverview = new CucumberCoverageOverview();
			cucumberCoverageOverview.setOutputDirectory("target");
			cucumberCoverageOverview.setOutputName("cucumber-results");
			cucumberCoverageOverview
					.setSourceFile(CommonVariable.getCurrentRootDir() + CommonVariable.getCucumberJsonReportPath());
			cucumberCoverageOverview.setExcludeCoverageTags(new String[] { "@flaky" });
			cucumberCoverageOverview.setIncludeCoverageTags(new String[] { "@passed" });
			cucumberCoverageOverview.execute(true);
			logger.info("Generating cucumber coverage overview report is completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber coverage overview report");
			logger.error(e.getMessage());
		}
	}

	public static void generateCucumberUsageReport() throws Exception {
		logger.info("Generating cucumber usage report is started");
		try {
			CucumberUsageReporting cucumberUsageReporting = new CucumberUsageReporting();
			cucumberUsageReporting.setOutputDirectory("target");
			cucumberUsageReporting
					.setJsonUsageFile(CommonVariable.getCurrentRootDir() + CommonVariable.getCucumberUsageJsonReportPath());
			cucumberUsageReporting.executeReport();
			logger.info("Generating cucumber usage report is completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber usage report");
			logger.error(e.getMessage());

		}
	}

	public static void generateCucumberChartReport() throws Exception {
		logger.info("Generating cucumber chart report is started");
		try {
			ExtendedRuntimeOptions extendedRuntimeOptions = new ExtendedRuntimeOptions();
			extendedRuntimeOptions.setOutputFolder("target");
			extendedRuntimeOptions.setReportPrefix("cucumber-reports");
			extendedRuntimeOptions.setJsonReportPaths(
					new String[] { CommonVariable.getCurrentRootDir() + CommonVariable.getCucumberJsonReportPath() });
			extendedRuntimeOptions.setOverviewReport(true);
			extendedRuntimeOptions.setCoverageReport(true);
			CucumberOverviewChartsReport cucumberOverviewChartsReport = new CucumberOverviewChartsReport(
					extendedRuntimeOptions);
			cucumberOverviewChartsReport.execute(true, true);
			logger.info("Generating cucumber chart report is completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber chart report");
			logger.error(e.getMessage());
		}
	}

	public static void generateCucumberResultsOverviewReport() throws Exception {
		logger.info("Generating cucumber results overview report is started");
		try {
			CucumberResultsOverview cucumberResultsOverview = new CucumberResultsOverview();
			cucumberResultsOverview.setOutputDirectory("target");
			cucumberResultsOverview.setOutputName("cucumber-results");
			cucumberResultsOverview.setSourceFile("target/cucumber-report.json");
			cucumberResultsOverview.execute();
			logger.info("Generating cucumber results overview report is completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber results overview report");
			logger.error(e.getMessage());
		}
	}

	public static void generateCucumberDetailedResultsReport() throws Exception {
		logger.info("Generating cucumber detailed results report is started");
		try {
			CucumberDetailedResults cucumberDetailedResults = new CucumberDetailedResults();
			cucumberDetailedResults.setOutputDirectory("target/");
			cucumberDetailedResults.setOutputName("cucumber-results");
			cucumberDetailedResults.setSourceFile("target/cucumber-report.json");
			cucumberDetailedResults.execute(true, false);
			logger.info("Generating cucumber detailed results report is completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber detailed results report");
			logger.error(e.getMessage());
		}
	}

	public static void generateCucumberFeatureOverviewReport() throws Exception {
		logger.info("Generating cucumber feature overview report is started");
		try {
			CucumberFeatureOverview cucumberFeatureOverview = new CucumberFeatureOverview();
			cucumberFeatureOverview.setOutputDirectory("target");
			cucumberFeatureOverview.setOutputName("cucumber-results");
			cucumberFeatureOverview.setSourceFile("target/cucumber-report.json");
			cucumberFeatureOverview.execute(true, true);
			logger.info("Generating cucumber feature overview report is completed");
		} catch (Exception e) {
			logger.error("Error in generating cucumber feature overview report");
			logger.error(e.getMessage());
		}
	}

	public static void openCucumberHtmlReport() throws Exception {
		logger.info("Opening cucumber html report is started");
		File cucumberReport = new File(
				System.getProperty("user.dir") + "/target/cucumber-html-reports/overview-features.html");
		Desktop desktop = Desktop.getDesktop();
		if (cucumberReport.exists()) {
			try {
				desktop.open(cucumberReport);
				logger.info("Opening cucumber html report is completed");
			} catch (IOException e) {
				logger.error("Error in opening cucumber html report");
				logger.error(e.getMessage());
			}
		}
	}

}
