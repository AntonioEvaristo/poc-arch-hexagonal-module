package br.com.ae.rest.handle;

import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.rest.handle.dto.ErroProdutoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionHandle {
    private final MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroProdutoDto> handleValidacao(MethodArgumentNotValidException exception) {
        List<ErroProdutoDto> errosProdutoDto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> errosProdutoDto
                .add(new ErroProdutoDto(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        HttpStatus.BAD_REQUEST.value(), getMensagemErro(e), LocalDateTime.now())));
        return errosProdutoDto;
    }
    private String getMensagemErro(FieldError e) {
        return messageSource.getMessage(e, LocaleContextHolder.getLocale());
    }

    @ExceptionHandler(ProdutoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroProdutoDto handleProduto(ProdutoException exception) {
        return new ErroProdutoDto(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.getMessage(), LocalDateTime.now());
    }

}
