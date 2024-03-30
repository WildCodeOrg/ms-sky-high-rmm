package org.skyhigh.msskyhighrmm.model.SystemObjects.Generators.BlockReason;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.skyhigh.msskyhighrmm.model.DBEntities.BlockReasonEntity;

import java.io.Serializable;
import java.util.Properties;

public class BlockReasonSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%010d";

    private String format;

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object object) throws HibernateException {
        return String.format(format, ((BlockReasonEntity)object).getId(), super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(type, parameters, serviceRegistry);
        String numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, parameters, NUMBER_FORMAT_DEFAULT).replace("%", "%2");
        this.format = "%1$s"+numberFormat;
    }
}
