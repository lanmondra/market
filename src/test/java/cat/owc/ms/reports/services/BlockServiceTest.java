//package cat.owc.ms.reports.services;
//
//import cat.owc.ms.reports.AbstractTest;
//import cat.owc.ms.reports.OwcReportsApplication;
//import cat.owc.ms.reports.config.ClientContextHolder;
//import cat.owc.ms.reports.dto.BlockDTO;
//import cat.owc.ms.reports.entity.Block;
//import cat.owc.ms.reports.entity.BlockTemplate;
//import cat.owc.ms.reports.entity.Form;
//import cat.owc.ms.reports.entity.Question;
//import cat.owc.ms.reports.entity.enumeration.FormStatus;
//import cat.owc.ms.reports.exceptions.MusicException;
//import cat.owc.ms.reports.mapper.BlockMapper;
//import cat.owc.ms.reports.repository.BlockRepository;
//import cat.owc.ms.reports.repository.FormRepository;
//import cat.owc.ms.reports.services.interfaces.IBlockService;
//import org.assertj.core.api.Condition;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {OwcReportsApplication.class })
//@Transactional
//public class BlockServiceTest extends AbstractTest {
//
//	@Autowired
//    private IBlockService blockService;
//
//	@Autowired
//    private BlockRepository blockRepository;
//
//
//	@Autowired
//    private FormRepository formRepository;
//	
//	@Autowired
//	private BlockMapper blockMapper;
//
//
//
//    @Test
//    @Transactional
//    public void addBlockOkTest() {
//        BlockDTO blockDTO = createBlockDTO(3, null);
//        long numBlocksBefore = blockRepository.count();
//
//        blockDTO = blockService.add(blockDTO);
//
//        long numBlockssAfter = blockRepository.count();
//
//        assertThat(numBlockssAfter).as("Debe incrementarse en uno el numero de bloques").isEqualTo(numBlocksBefore + 1);
//        assertThat(blockDTO.getId()).as("Id debe estar informado").isNotNull();
//        assertThat(blockDTO.getUuid()).as("Uuid debe estar informado").isNotNull();
//        assertThat(blockDTO.getName()).as("El nombre debe ser Block prueba permisos").isEqualTo("Block prueba permisos");
//    }
//
//
//
//    @Test
//    @Transactional
//    public void addBlockNokTest() {
//        BlockDTO blockDTO = blockService.findBlockByUuidDTO("1111");
//        blockDTO.setName(null);
//        checkAddUpdateBlock(blockDTO, "Nombre no informado", IBlockServiceErrorCode.MISSING_NAME, false);
//
//        blockDTO.setName("AAAA");
//        blockDTO.setOrderNum(null);
//        checkAddUpdateBlock(blockDTO, "Order num no informado", IBlockServiceErrorCode.MISSING_ORDER_NUM, false);
//
//        blockDTO.setOrderNum(1);
//        checkAddUpdateBlock(blockDTO, "Bloque esta en form activo", IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE, false);
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateBlockOkTest() {
//        setFormPending(7);
//        BlockDTO blockDTO = blockService.findBlockByUuidDTO("4444");
//        blockDTO.setName("new name");
//        blockDTO.setOrderNum(99);
//
//        BlockDTO updatedBlockDTO = blockService.update(blockDTO );
//
//        assertThat(updatedBlockDTO.getName()).as("El nombre debe haber cambiado").isEqualTo("new name");
//        assertThat(updatedBlockDTO.getOrderNum()).as("Order num debe haber cambiado" ).isEqualTo(99);
//        assertThat(updatedBlockDTO.getUuid()).as("Uuid no debe haber cambiaod").isEqualTo(blockDTO.getUuid());
//    }
//
//
//
//    @Test
//    @Transactional
//    public void updateBlockNokTest() {
//        BlockDTO blockDTO = blockService.findBlockByUuidDTO("1111");
//        blockDTO.setName(null);
//        checkAddUpdateBlock(blockDTO, "Nombre no informado", IBlockServiceErrorCode.MISSING_NAME, false);
//
//        blockDTO.setName("AAAA");
//        blockDTO.setOrderNum(null);
//        checkAddUpdateBlock(blockDTO, "Order num no informado", IBlockServiceErrorCode.MISSING_ORDER_NUM, false);
//
//        blockDTO.setOrderNum(1);
//        checkAddUpdateBlock(blockDTO, "Bloque esta en form activo", IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE, false);
//
//    }
//
//
//
//    @Test
//    @Transactional
//    public void deleteBlockOkTest() {
//        setFormPending(7);
//        BlockDTO blockDTO = blockService.findBlockByUuidDTO("4444");
//
//        blockService.delete(blockDTO.getUuid(), true);
//
//        Block block = blockRepository.findById(4).get();
//        assertThat(block.getDeleted()).as("Deleted debe estar informado").isNotNull();
//    }
//    
//    @Test
//    @Transactional
//    public void deleteBlockTest() {
//    	BlockDTO blockDTO = blockService.findBlockByUuidDTO("4444");
//    	
//    	blockService.delete(blockDTO.getUuid(), false);
//    	blockRepository.flush();
//    	
//    
//    	
//    	 Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(IBlockServiceErrorCode.BLOCK_NOT_FOUND),
//                 " Expected error code " + IBlockServiceErrorCode.BLOCK_NOT_FOUND);
//    	
//    	 assertThatExceptionOfType(MusicException.class)
//               .isThrownBy(() -> blockService.findBlockByUuidDTO("4444"))
//               .has(errorCode);
//    	
//    }
//    
//
//
//
//
//    public static BlockDTO createBlockDTO(Integer pollId, Integer reportId) {
//        BlockDTO blockDTO = new BlockDTO();
//        blockDTO.setName("Block prueba permisos");
//        blockDTO.setDescription("Descripcion del bloque");
//        blockDTO.setIsVisibleSubject(true);
//        blockDTO.setOrderNum(1);
//        if (pollId != null) {
//            blockDTO.setPollId(pollId);
//        }
//
//        if (reportId != null) {
//            blockDTO.setReportTemplateId(reportId);
//        }
//
//        return blockDTO;
//
//    }
//
//
//    private void checkAddUpdateBlock(BlockDTO blockDTO, String description, String expectedErrorCode, Boolean update) {
//        Condition<MusicException> errorCode = new Condition<>(reportsException -> reportsException.getCode().equals(expectedErrorCode),
//                description + " Expected error code " + expectedErrorCode);
//
//        assertThatExceptionOfType(MusicException.class)
//                .isThrownBy(() -> {
//                    if (update) {
//                        blockService.update(blockDTO );
//                    }
//                    else {
//                        blockService.add(blockDTO);
//                    }
//                })
//                .has(errorCode);
//    }
//
//
//    private void setFormPending(Integer formId) {
//        // Forzar el estado del form asociado al report para passar el test
//        Form form = formRepository.findById(formId).get();
//        form.setStatus(FormStatus.PENDING);
//        formRepository.saveAndFlush(form);
//
//    }
//
//
//    @Test
//    @Transactional
//    public void createFromTemplateTest() {
//        Block block = blockService.createFromTemplate(BlockTemplate.TYPE_MATCH_REPORT, ClientContextHolder.getFederation());
//
//        assertThat(block).as("No debe ser null").isNotNull();
//        assertThat(block.getQuestions()).as("Preguntas no debe ser null").isNotNull();
//        assertThat(block.getQuestions()).as("Debe tener 14 preguntas").hasSize(14);
//
//        Question question1 = block.getQuestions().stream()
//                .filter(question -> question.getOrderNum().equals(1))
//                .findFirst()
//                .orElse(null);
//
//        assertThat(question1).as("No debe ser null").isNotNull();
//
//        // Verificar que el id y el uuid se generan de nuevo
//        assertThat(question1.getId()).as("El id no debe coincidir con el de la pregunta asociada al template").isNotEqualTo(37);
//        assertThat(question1.getUuid()).as("El uuid no debe coincidir con el de la pregunta asociada al template").isNotEqualTo("3737");
//
//    }
//    
//    //--------- nuevo test
//    @Transactional
//    @Test
//    public void changeBlockWitnquestions () {
//    BlockDTO blockDTO=blockService.findBlockByUuidDTO("4444");
//    blockDTO.setPollId(2);
//    
//
//  
//  Condition<MusicException> errorCode = new Condition<>(reportsException -> 
//  reportsException.getCode().equals(IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE),
//          " Expected error code " + IBlockServiceErrorCode.BLOCK_CONTAINER_IS_ACTIVE);
//	
//	 assertThatExceptionOfType(MusicException.class)
//        .isThrownBy(() -> blockService.update(blockDTO))
//        .has(errorCode);
//    }
//    
//    @Transactional
//    @Test
//    public void editBlockWithQuestionTest() {
//    	
//    	BlockDTO blockDTO = blockService.findBlockByUuidDTO("4444");
//    	blockDTO.setName("Nuevo nombre");
//    	
//    	
//    	BlockDTO blockDTOUpdated = blockService.update(blockDTO);
//    	
//    	assertThat(blockDTOUpdated).as("si no es null es por que devuelve algo ").isNotNull();
//    	
//    	
//    	
//    }
//    
//    
//
//}
