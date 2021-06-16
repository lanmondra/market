//package cat.owc.ms.reports.excel;
//
//
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import cat.owc.ms.reports.dto.AnswerDTO;
//import cat.owc.ms.reports.dto.AnswerOptionDTO;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.dto.FormDTO;
//import cat.owc.ms.reports.dto.QuestionDTO;
//import cat.owc.ms.reports.entity.enumeration.AnswerTypeCode;
//import cat.owc.ms.reports.utils.I18n;
//
//@Component
//public class AnswersExcelGenerator extends AbstractExcelGenerator {
//
//	@Autowired
//	private I18n i18;
//
//
//    /**
//     * Genera el excel de respuestas agregadas en base a los  bloques que la componen y la lista de formularios respondidos
//     * @param blocks
//     * @param formsDTO
//     * @return
//     */
//    public ByteArrayOutputStream generateAggregatedAnswerExcel(List<BlockDTO> blocks, List<FormDTO> formsDTO){
//        // Crear el libro
//		XSSFWorkbook workbook = createWorkbook();
//
//        // Crear la hoja excel
//        String sheetName = i18.getMessage("aggregatedExcel");
//		XSSFSheet sheet = addShettToWorkbook(workbook, sheetName);
//
//		Map<Integer, Integer> rowPosition = new HashMap<>();
//		Map<String, Integer> offsetPosition = new HashMap<>();
//
//		int row = 1;
//		int col = 1;
//
//
//		// Inicialitzar files i columnes de excel i calcular posicions per cada pregunta i opcions de resposta
//		for (BlockDTO blockDTO : blocks) {
//			for(QuestionDTO questionDTO : blockDTO.getQuestions()) {
//				if (notAggregateQuestion(questionDTO)) {
//					continue;
//				}
//
//				// Posar el text de la pregunta com a primera col de la fila
//				setCellValue(sheet, questionDTO.getValue(), row, 0);
//				rowPosition.put(questionDTO.getId(), row);
//				row++;
//
//
//				// Posar com a columes totes les opcions de resposta
//				if(questionDTO.getAnswerTypeCode() == AnswerTypeCode.NUMERIC) {
//					List<Integer> numericValueList = getNumericValuesList(questionDTO); // llista dels valors numerics
//					for (Integer answerValue : numericValueList) {
//						setCellValue(sheet, answerValue, 0, col);
//						offsetPosition.put(getKey(questionDTO.getId(), answerValue), col);
//						col++;
//					}
//				}
//				else {
//					for (AnswerOptionDTO answerOptionDTO : questionDTO.getAnswerOptions()) {
//						setCellValue(sheet, answerOptionDTO.getValue(), 0, col);
//						offsetPosition.put(getKey(questionDTO.getId(), answerOptionDTO.getId()), col);
//						col++;
//					}
//				}
//			}
//		}
//
//
//		// Fer el calcul del número de respostes dels formularis
//		Map<String, Integer> aggregatedValues = getCountAggregatedMap(formsDTO);
//		fillExcelWithAggregatedValues(sheet, aggregatedValues, rowPosition, offsetPosition);
//
//
//        // Convertir el excel a byteArray
//        return workbookToBos(workbook);
//    }
//
//
//
//
//	private boolean notAggregateQuestion(QuestionDTO questionDTO) {
//		
//		
//		return !questionDTO.getHasAnswer() || questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.OPEN) 
//				|| questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.LINK);
//		
//	}
//
//
//	private String getKey(Integer questionId, Integer answerOptionID) {
//		return questionId + "-" + answerOptionID;
//	}
//
//
//	private String getKey(Integer questionId, String value) {
//		return questionId + "-" + value;
//	}
//
//
//    private void fillExcelWithAggregatedValues(XSSFSheet sheet, Map<String, Integer> aggregatedValues, Map<Integer, Integer> rows, Map<String, Integer> cols) {
//    	for (String questionKey : aggregatedValues.keySet()) {
//
//    		// La clau es questionId-<algo mes> Separar per "-" per obtenir el question id
//    		String [] keyParts = questionKey.split("-");
//    		Integer questionId = Integer.valueOf(keyParts[0]);
//
//    		Integer row = rows.get(questionId);
//    		Integer col = cols.get(questionKey);
//    		Integer numAnswers = aggregatedValues.get(questionKey);
//
//			setCellValue(sheet, numAnswers, row, col);
//		}
//	}
//
//
//
//
//    /**
//     * Devuelve una Lista de Integers con todos los posibles valores de respuesta a una pregunta numérica dados los valores máximo y mínimo de ésta
//     * @param questionDTO
//     * @return List<Integer>
//     */
//    private List<Integer> getNumericValuesList(QuestionDTO questionDTO){
//
//		List<AnswerOptionDTO> answerOptionsDTO = questionDTO.getAnswerOptions();
//		int rango1 = Integer.valueOf(answerOptionsDTO.get(0).getValue());
//		int rango2 = Integer.valueOf(answerOptionsDTO.get(1).getValue());
//		int minimum = Math.min(rango1, rango2);
//		int maximum = Math.max(rango1, rango2);
//
//    	return IntStream.rangeClosed(minimum, maximum).boxed().collect(Collectors.toList());
//    }
//
//
//    /**
//     * Hace el cálculo de la cantidad de respuestas de cada tipo y las guarda en un Map
//     * key = questionId-answerId(o value), value = resultado del cálculo de respuestas
//     * @param formsDTO
//     * @return Map<String, Integer>
//     */
//
//	private Map<String, Integer> getCountAggregatedMap(List<FormDTO> formsDTO){
//		Map<String, Integer> aggregatedValues = new HashMap<>();
//
//		for (FormDTO formDTO: formsDTO) {
//			for (BlockDTO blockDTO: formDTO.getBlocks()) {
//				for (QuestionDTO questionDTO : blockDTO.getQuestions()) {
//					if (notAggregateQuestion(questionDTO) || (questionDTO.getAnswer() == null)) {
//						continue;
//					}
//
//					if (questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.NUMERIC)) {
//						String answerKey = getKey(questionDTO.getId(), questionDTO.getAnswer().getValue());
//						incrementAnswerCounter(aggregatedValues, answerKey);
//					}
//					else {
//						for (Integer selectedAnswerOptionId : questionDTO.getAnswer().getSelectedAnswerOptions()) {
//							String answerKey = getKey(questionDTO.getId(), selectedAnswerOptionId);
//							incrementAnswerCounter(aggregatedValues, answerKey);
//						}
//					}
//				}
//			}
//		}
//
//		return aggregatedValues;
//	}
//
//
//	private void incrementAnswerCounter(Map<String, Integer> aggregatedValues, String answerKey) {
//		// Incrementar el comptador associat a aquella resposta
//		Integer counter = aggregatedValues.getOrDefault(answerKey, 0);
//		counter++;
//		aggregatedValues.put(answerKey, counter);
//	}
//
//
//
//
//
//    /**
//     * Genera el excel de respuestas detalladas
//     * @param blocks
//     * @param formsDTO
//     * @return
//     */
//    public ByteArrayOutputStream generateDetailedAnswerExcel(List<BlockDTO> blocks, List<FormDTO> formsDTO){
//        // Crear el libro
//        XSSFWorkbook workbook = createWorkbook();
//
//        // Crear la hoja excel
//        String sheetName = i18.getMessage("detailExcel");
//        XSSFSheet sheet = addShettToWorkbook(workbook, sheetName);
//
//
//        // Bucle per omplir la fila 1 del excel amb el text de les preguntes i la fila 2 amb totes les possibles respostes
//        Map<String, Integer> colPositions = new HashMap<>();
//        int col = 1;
//        for (BlockDTO blockDTO : blocks) {
//        	for (QuestionDTO questionDTO : blockDTO.getQuestions()) {
//        		if (!questionDTO.getHasAnswer()) {
//        			continue;
//				}
//
//        		setCellValue(sheet, questionDTO.getValue(), 0, col);
//
//        		if (questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.OPEN) || questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.LINK)) {
//        			colPositions.put(String.valueOf(questionDTO.getId()), col++);
//				}
//        		else if (questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.NUMERIC)) {
//        			List<Integer> values = getNumericValuesList(questionDTO);
//        			for (Integer value : values) {
//						setCellValue(sheet, value, 1, col);
//						colPositions.put(getKey(questionDTO.getId(), value), col++);
//					}
//				}
//        		else {
//        			for (AnswerOptionDTO answerOptionDTO : questionDTO.getAnswerOptions()) {
//        				setCellValue(sheet, answerOptionDTO.getValue(), 1, col);
//        				colPositions.put(getKey(questionDTO.getId(), answerOptionDTO.getId()), col++);
//					}
//				}
//
//			}
//		}
//
//        // Per cada form, processar les respostes i colocar-les al excel
//		int row = 2;
//		for (FormDTO form : formsDTO) {
//			fillFormRow(sheet, row++, form, colPositions);
//		}
//
//		// Convertir el excel a byteArray
//		return workbookToBos(workbook);
//
//    }
//
//
//    private void fillFormRow(XSSFSheet sheet, int row, FormDTO formDTO, Map<String, Integer> colPositions) {
//    	// Posar uuid del que respon
//    	setCellValue(sheet, formDTO.getInformerUuid(), row, 0);
//
//    	// Posar la resposta a les preguntes del form
//    	for (BlockDTO blockDTO : formDTO.getBlocks()) {
//    		for (QuestionDTO questionDTO : blockDTO.getQuestions()) {
//
//				AnswerDTO answerDTO = questionDTO.getAnswer();
//				if (answerDTO == null) {
//					continue;
//				}
//
//				if (questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.OPEN) || questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.LINK)) {
//					int col = colPositions.get(String.valueOf(questionDTO.getId()));
//					setCellValue(sheet, answerDTO.getValue(), row, col);
//				}
//				else if (questionDTO.getAnswerTypeCode().equals(AnswerTypeCode.NUMERIC)) {
//					int col = colPositions.get(getKey(questionDTO.getId(), answerDTO.getValue()));
//					setCellValue(sheet, "X", row, col);
//				}
//				else {
//					for (Integer selectedOptionId : answerDTO.getSelectedAnswerOptions()) {
//						int col = colPositions.get(getKey(questionDTO.getId(), selectedOptionId));
//						setCellValue(sheet, "X", row, col);
//					}
//				}
//			}
//		}
//	}
//
//
//
//}
//
//
