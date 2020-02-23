package ir.bourse.service;

import ir.bourse.domain.Sign;
import ir.bourse.repository.SignRepository;
import ir.bourse.service.dto.SignDTO;
import ir.bourse.service.mapper.SignMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Sign}.
 */
@Service
@Transactional
public class SignService {

    private final Logger log = LoggerFactory.getLogger(SignService.class);

    private final SignRepository signRepository;

    private final SignMapper signMapper;

    public SignService(SignRepository signRepository, SignMapper signMapper) {
        this.signRepository = signRepository;
        this.signMapper = signMapper;
    }

    /**
     * Save a sign.
     *
     * @param signDTO the entity to save.
     * @return the persisted entity.
     */
    public SignDTO save(SignDTO signDTO) {
        log.debug("Request to save Sign : {}", signDTO);
        Sign sign = signMapper.toEntity(signDTO);
        sign = signRepository.save(sign);
        return signMapper.toDto(sign);
    }

    /**
     * Get all the signs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SignDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Signs");
        return signRepository.findAll(pageable)
            .map(signMapper::toDto);
    }


    /**
     * Get one sign by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SignDTO> findOne(Long id) {
        log.debug("Request to get Sign : {}", id);
        return signRepository.findById(id)
            .map(signMapper::toDto);
    }

    /**
     * Delete the sign by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Sign : {}", id);
        signRepository.deleteById(id);
    }
}
