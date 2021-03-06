/**
 * NodeRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cos.org.datacontract.schemas._2004._07.WCFService_Portal;

public class NodeRequest  extends cos.org.datacontract.schemas._2004._07.WCFService_Portal.Request  implements java.io.Serializable {
    private Boolean DESC;

    private java.util.Calendar date;

    private String id;

    private Boolean LOAD;

    private int mode;

    private String networkId;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

    private Boolean paramSearch;

    private Object[] searchParameters;

    private Boolean textSearch;

    private Integer textSearchMode;

    private cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user;

    private String whereClause;

    public NodeRequest() {
    }

    public NodeRequest(
           Boolean DESC,
           java.util.Calendar date,
           String id,
           Boolean LOAD,
           int mode,
           String networkId,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes,
           String orderBy,
           Integer page,
           Integer pageSize,
           Boolean paramSearch,
           Object[] searchParameters,
           Boolean textSearch,
           Integer textSearchMode,
           cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user,
           String whereClause) {
        this.DESC = DESC;
        this.date = date;
        this.id = id;
        this.LOAD = LOAD;
        this.mode = mode;
        this.networkId = networkId;
        this.nodes = nodes;
        this.orderBy = orderBy;
        this.page = page;
        this.pageSize = pageSize;
        this.paramSearch = paramSearch;
        this.searchParameters = searchParameters;
        this.textSearch = textSearch;
        this.textSearchMode = textSearchMode;
        this.user = user;
        this.whereClause = whereClause;
    }


    /**
     * Gets the DESC value for this NodeRequest.
     *
     * @return DESC
     */
    public Boolean getDESC() {
        return DESC;
    }


    /**
     * Sets the DESC value for this NodeRequest.
     *
     * @param DESC
     */
    public void setDESC(Boolean DESC) {
        this.DESC = DESC;
    }


    /**
     * Gets the date value for this NodeRequest.
     *
     * @return date
     */
    public java.util.Calendar getDate() {
        return date;
    }


    /**
     * Sets the date value for this NodeRequest.
     *
     * @param date
     */
    public void setDate(java.util.Calendar date) {
        this.date = date;
    }


    /**
     * Gets the id value for this NodeRequest.
     *
     * @return id
     */
    public String getId() {
        return id;
    }


    /**
     * Sets the id value for this NodeRequest.
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the LOAD value for this NodeRequest.
     *
     * @return LOAD
     */
    public Boolean getLOAD() {
        return LOAD;
    }


    /**
     * Sets the LOAD value for this NodeRequest.
     *
     * @param LOAD
     */
    public void setLOAD(Boolean LOAD) {
        this.LOAD = LOAD;
    }


    /**
     * Gets the mode value for this NodeRequest.
     *
     * @return mode
     */
    public int getMode() {
        return mode;
    }


    /**
     * Sets the mode value for this NodeRequest.
     *
     * @param mode
     */
    public void setMode(int mode) {
        this.mode = mode;
    }


    /**
     * Gets the networkId value for this NodeRequest.
     *
     * @return networkId
     */
    public String getNetworkId() {
        return networkId;
    }


    /**
     * Sets the networkId value for this NodeRequest.
     *
     * @param networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }


    /**
     * Gets the nodes value for this NodeRequest.
     *
     * @return nodes
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes getNodes() {
        return nodes;
    }


    /**
     * Sets the nodes value for this NodeRequest.
     *
     * @param nodes
     */
    public void setNodes(cos.org.datacontract.schemas._2004._07.LINQ2Entities.Nodes nodes) {
        this.nodes = nodes;
    }


    /**
     * Gets the orderBy value for this NodeRequest.
     *
     * @return orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }


    /**
     * Sets the orderBy value for this NodeRequest.
     *
     * @param orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }


    /**
     * Gets the page value for this NodeRequest.
     *
     * @return page
     */
    public Integer getPage() {
        return page;
    }


    /**
     * Sets the page value for this NodeRequest.
     *
     * @param page
     */
    public void setPage(Integer page) {
        this.page = page;
    }


    /**
     * Gets the pageSize value for this NodeRequest.
     *
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }


    /**
     * Sets the pageSize value for this NodeRequest.
     *
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * Gets the paramSearch value for this NodeRequest.
     *
     * @return paramSearch
     */
    public Boolean getParamSearch() {
        return paramSearch;
    }


    /**
     * Sets the paramSearch value for this NodeRequest.
     *
     * @param paramSearch
     */
    public void setParamSearch(Boolean paramSearch) {
        this.paramSearch = paramSearch;
    }


    /**
     * Gets the searchParameters value for this NodeRequest.
     *
     * @return searchParameters
     */
    public Object[] getSearchParameters() {
        return searchParameters;
    }


    /**
     * Sets the searchParameters value for this NodeRequest.
     *
     * @param searchParameters
     */
    public void setSearchParameters(Object[] searchParameters) {
        this.searchParameters = searchParameters;
    }


    /**
     * Gets the textSearch value for this NodeRequest.
     *
     * @return textSearch
     */
    public Boolean getTextSearch() {
        return textSearch;
    }


    /**
     * Sets the textSearch value for this NodeRequest.
     *
     * @param textSearch
     */
    public void setTextSearch(Boolean textSearch) {
        this.textSearch = textSearch;
    }


    /**
     * Gets the textSearchMode value for this NodeRequest.
     *
     * @return textSearchMode
     */
    public Integer getTextSearchMode() {
        return textSearchMode;
    }


    /**
     * Sets the textSearchMode value for this NodeRequest.
     *
     * @param textSearchMode
     */
    public void setTextSearchMode(Integer textSearchMode) {
        this.textSearchMode = textSearchMode;
    }


    /**
     * Gets the user value for this NodeRequest.
     *
     * @return user
     */
    public cos.org.datacontract.schemas._2004._07.LINQ2Entities.User getUser() {
        return user;
    }


    /**
     * Sets the user value for this NodeRequest.
     *
     * @param user
     */
    public void setUser(cos.org.datacontract.schemas._2004._07.LINQ2Entities.User user) {
        this.user = user;
    }


    /**
     * Gets the whereClause value for this NodeRequest.
     *
     * @return whereClause
     */
    public String getWhereClause() {
        return whereClause;
    }


    /**
     * Sets the whereClause value for this NodeRequest.
     *
     * @param whereClause
     */
    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof NodeRequest)) return false;
        NodeRequest other = (NodeRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.DESC==null && other.getDESC()==null) ||
             (this.DESC!=null &&
              this.DESC.equals(other.getDESC()))) &&
            ((this.date==null && other.getDate()==null) ||
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.id==null && other.getId()==null) ||
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.LOAD==null && other.getLOAD()==null) ||
             (this.LOAD!=null &&
              this.LOAD.equals(other.getLOAD()))) &&
            this.mode == other.getMode() &&
            ((this.networkId==null && other.getNetworkId()==null) ||
             (this.networkId!=null &&
              this.networkId.equals(other.getNetworkId()))) &&
            ((this.nodes==null && other.getNodes()==null) ||
             (this.nodes!=null &&
              this.nodes.equals(other.getNodes()))) &&
            ((this.orderBy==null && other.getOrderBy()==null) ||
             (this.orderBy!=null &&
              this.orderBy.equals(other.getOrderBy()))) &&
            ((this.page==null && other.getPage()==null) ||
             (this.page!=null &&
              this.page.equals(other.getPage()))) &&
            ((this.pageSize==null && other.getPageSize()==null) ||
             (this.pageSize!=null &&
              this.pageSize.equals(other.getPageSize()))) &&
            ((this.paramSearch==null && other.getParamSearch()==null) ||
             (this.paramSearch!=null &&
              this.paramSearch.equals(other.getParamSearch()))) &&
            ((this.searchParameters==null && other.getSearchParameters()==null) ||
             (this.searchParameters!=null &&
              java.util.Arrays.equals(this.searchParameters, other.getSearchParameters()))) &&
            ((this.textSearch==null && other.getTextSearch()==null) ||
             (this.textSearch!=null &&
              this.textSearch.equals(other.getTextSearch()))) &&
            ((this.textSearchMode==null && other.getTextSearchMode()==null) ||
             (this.textSearchMode!=null &&
              this.textSearchMode.equals(other.getTextSearchMode()))) &&
            ((this.user==null && other.getUser()==null) ||
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.whereClause==null && other.getWhereClause()==null) ||
             (this.whereClause!=null &&
              this.whereClause.equals(other.getWhereClause())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getDESC() != null) {
            _hashCode += getDESC().hashCode();
        }
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLOAD() != null) {
            _hashCode += getLOAD().hashCode();
        }
        _hashCode += getMode();
        if (getNetworkId() != null) {
            _hashCode += getNetworkId().hashCode();
        }
        if (getNodes() != null) {
            _hashCode += getNodes().hashCode();
        }
        if (getOrderBy() != null) {
            _hashCode += getOrderBy().hashCode();
        }
        if (getPage() != null) {
            _hashCode += getPage().hashCode();
        }
        if (getPageSize() != null) {
            _hashCode += getPageSize().hashCode();
        }
        if (getParamSearch() != null) {
            _hashCode += getParamSearch().hashCode();
        }
        if (getSearchParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSearchParameters());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getSearchParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTextSearch() != null) {
            _hashCode += getTextSearch().hashCode();
        }
        if (getTextSearchMode() != null) {
            _hashCode += getTextSearchMode().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getWhereClause() != null) {
            _hashCode += getWhereClause().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NodeRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NodeRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "DESC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LOAD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "LOAD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Mode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "NetworkId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Nodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "Nodes"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderBy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "OrderBy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "Page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "PageSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paramSearch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "ParamSearch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("searchParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "SearchParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "anyType"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textSearch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TextSearch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("textSearchMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "TextSearchMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/LINQ2Entities", "User"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("whereClause");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/WCFService.Portal", "WhereClause"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
