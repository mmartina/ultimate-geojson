package com.erumi.ugeojson.model.feature;

import com.erumi.ugeojson.model.GeoJSONObjectDto;
import com.erumi.ugeojson.model.GeoJSONObjectTypeEnum;
import com.erumi.ugeojson.model.geometry.GeometryDto;

/**
 * @author moksuzer
 *
 */
public class FeatureDto extends GeoJSONObjectDto {

	private static final long serialVersionUID = 1L;

	private GeometryDto geometry;
	private String properties;
	private String id;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Feature;
	}

	public GeometryDto getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryDto geometry) {
		this.geometry = geometry;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
