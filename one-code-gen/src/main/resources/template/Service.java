package ${servicePackageName};

import ${beanClassName};
import ${daoClassName};
import com.lcw.one.util.service.CrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${serviceSimpleName} extends CrudService<${daoSimpleName}, ${beanSimpleName}> {

}
