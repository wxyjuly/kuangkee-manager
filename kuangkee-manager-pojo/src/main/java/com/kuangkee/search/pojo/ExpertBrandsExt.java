package com.kuangkee.search.pojo ;

public class ExpertBrandsExt {
	
    private Long id; //专家id
    
    private Long brandId; //brandId
    
    private String brandName; //brandName

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Override
	public String toString() {
		return "ExpertBrandsExt [id=" + id + ", brandId=" + brandId + ", brandName=" + brandName + "]";
	}

}