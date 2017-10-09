package mybatis.framework;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 动态数据库切换
 * @author xxp
 * @since 2016年11月3日 下午8:44:37
 * @version
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
	    return CustomizedContextHolder.getCustomerType();
	}
}
