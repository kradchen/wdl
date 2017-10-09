package mybatis.framework;

import java.util.HashSet;

public class UsingDataSourceSet {
	
	private String defaultDataBaseMapperName;
	
	/**
	 * @return the defaultDataBaseMapperName
	 */
	public String getDefaultDataBaseMapperName() {
		return defaultDataBaseMapperName;
	}

	/**
	 * @param defaultDataBaseMapperName the defaultDataBaseMapperName to set
	 */
	public void setDefaultDataBaseMapperName(String defaultDataBaseMapperName) {
		this.defaultDataBaseMapperName = defaultDataBaseMapperName;
	}
	private HashSet<String> typeMapper;

	/**
	 * @return the typeMapper
	 */
	public HashSet<String> getTypeMapper() {
		return typeMapper;
	}

	/**
	 * @param typeMapper the typeMapper to set
	 */
	public void setTypeMapper(HashSet<String> typeMapper) {
		this.typeMapper = typeMapper;
	}
	
}
