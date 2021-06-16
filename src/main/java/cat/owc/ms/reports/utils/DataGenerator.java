//package cat.owc.ms.reports.utils;
//
//import cat.owc.ms.reports.entity.*;
//import cat.owc.ms.reports.entity.enumeration.AnswerTypeCode;
//import cat.owc.ms.reports.repository.AnswerOptionRepository;
//import cat.owc.ms.reports.repository.AnswerTypeRepository;
//import cat.owc.ms.reports.repository.BlockRepository;
//import cat.owc.ms.reports.repository.QuestionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//
///**
// * Clase de utilidad para generación de datos de prueba (blocks, question y answer options)
// */
//public class DataGenerator {
//
//    @Autowired
//    private AnswerTypeRepository answerTypeRepository;
//
//    @Autowired
//    private AnswerOptionRepository answerOptionRepository;
//
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private BlockRepository blockRepository;
//
//
//    public List<Block> getBlocks() {
//        List<Block> blocks = new ArrayList<>();
//        //List<Block> blocks = blockRepository.findAll();
//        //List<BlockDTO> blockDTOSNoQuestions = blocks.stream().map(blockMapper::toDtoNoQuestions).collect(Collectors.toList());
//        //List<BlockDTO> blockDTOS = blocks.stream().map(blockMapper::toDto).collect(Collectors.toList());
//
//        //log.info("LLista de blocsk dto {}", blockDTOS);
//
//        for (int i = 1; i<7; i++) {
//            Block block = new Block();
//            //block.setId(i);
//            block.setName("Bloc numero " + i);
//            block.setDescription("La bonica descripció del bloc num " + i);
//            block.setOrderNum(i);
//            block.setQuestions(null);
//            block = blockRepository.saveAndFlush(block);
//
//            block.setQuestions(getQuestions(block));
//            block = blockRepository.saveAndFlush(block);
//
//            blocks.add(block);
//        }
//
//        return blocks;
//    }
//
//
//
//    public Set<Question> getQuestions(Block block) {
//        Set<Question> questions = new HashSet<>();
//
//        for (int i = 1; i<7; i++) {
//            Question question = new Question();
//            //question.setId((block.getId() * 10) + i);
//            question.setValue(String.format("Pregunta %d del bloc %d", i, block.getId() ));
//            question.setHasAnswer(true);
//            question.setMandatory(true);
//            question.setOrderNum(i+1);
//            question.setAnswerType(getAnswerType(i));
//            question.setAnswerOptions(getAnswerOptions(question));
//            question = questionRepository.saveAndFlush(question);
//
//            questions.add(question);
//        }
//
//        return questions;
//    }
//
//
//
//    public Set<AnswerOption> getAnswerOptions(Question question) {
//        Set<AnswerOption> options = new HashSet<>();
//
//        Integer numOptions = 0;
//        switch (question.getAnswerType().getCode()) {
//            case BINARY: numOptions = 2; break;
//            case EXCLUDING: numOptions = 3; break;
//            case MULTIPLE:  numOptions = 3; break;
//            case NUMERIC: numOptions = 2; break;
//            case EXCLUDING_COLOR: numOptions = 4; break;
//            default: return null;
//        }
//
//        for (int i = 0; i<numOptions; i++) {
//            AnswerOption option = new AnswerOption();
//            //option.setId(i);
//            option.setValue("Text opcio " + i);
//            option.setOrderNum(i+1);
//            if (question.getAnswerType().getCode() == AnswerTypeCode.NUMERIC) {
//                option.setValue("" + (i == 0 ? i+1 : i+9));     // Rang de valors entre 1 i 10
//            }
//            else if (question.getAnswerType().getCode() == AnswerTypeCode.EXCLUDING_COLOR) {
//                option.setColor(getColor(i));
//                option.setDescription("Descripcion de la opció " + i);
//            }
//
//            option = answerOptionRepository.saveAndFlush(option);
//
//
//            options.add(option);
//        }
//
//        return options;
//    }
//
//
//    public String getColor(Integer i) {
//        switch (i) {
//            case 0: return "F91205";
//            case 1: return "19F905";
//            case 2: return "F9F505";
//            case 3: return "052EF9";
//        }
//        return "000000";
//    }
//
//
//
//
//    public AnswerType getAnswerType(Integer id) {
//        Integer idAnswerType = (id % 7);
//        return answerTypeRepository.findById(id).get();
//    }
//
//
//
//}
