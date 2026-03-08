// Description: Java 25 interface for a Relation record implementation

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package server.markhome.mcf.v3_1.cfbam.cfbam;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
//import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public interface ICFBamRelation extends ICFBamScope
{
	public static final ICFBamSchema.RelationTypeEnum RELATIONTYPE_MIN_VALUE = ICFBamSchema.RelationTypeEnum.Unknown;
	public static final ICFBamSchema.RelationTypeEnum RELATIONTYPE_MAX_VALUE = ICFBamSchema.RelationTypeEnum.Children;
	public static final String S_TABLEID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 TABLEID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_TABLEID_INIT_VALUE );
	public static final String S_DEFSCHEMAID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 DEFSCHEMAID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_DEFSCHEMAID_INIT_VALUE );
	public static final String NAME_INIT_VALUE = new String( "" );
	public static final ICFBamSchema.RelationTypeEnum RELATIONTYPE_INIT_VALUE = ICFBamSchema.ordinalToRelationTypeEnum( 0 );
	public static final String S_FROMINDEXID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 FROMINDEXID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_FROMINDEXID_INIT_VALUE );
	public static final String S_TOTABLEID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 TOTABLEID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_TOTABLEID_INIT_VALUE );
	public static final String S_TOINDEXID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 TOINDEXID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_TOINDEXID_INIT_VALUE );
	public final static boolean ISREQUIRED_INIT_VALUE = false;
	public final static boolean ISXSDCONTAINER_INIT_VALUE = false;
	public final static boolean ISLATERESOLVER_INIT_VALUE = false;
	public final static boolean ALLOWADDENDUM_INIT_VALUE = false;
	public static final String S_NARROWEDID_INIT_VALUE = "$switch HasInitValue yes InitValue default Zero256bits$";
	public static final CFLibDbKeyHash256 NARROWEDID_INIT_VALUE = CFLibDbKeyHash256.fromHex( S_NARROWEDID_INIT_VALUE );
	public final static int CLASS_CODE = 0xa835;
	public final static String S_CLASS_CODE = "a835";

	public ICFBamSchemaDef getOptionalLookupDefSchema();
	public ICFBamTable getRequiredContainerFromTable();
	public List<ICFBamRelationCol> getOptionalComponentsColumns();
	public List<ICFBamPopTopDep> getOptionalComponentsPopDep();
	public ICFBamIndex getRequiredLookupFromIndex();
	public ICFBamTable getRequiredLookupToTable();
	public ICFBamIndex getRequiredLookupToIndex();
	public ICFBamRelation getOptionalLookupNarrowed();
	public void setOptionalLookupDefSchema(ICFBamSchemaDef argObj);
	public void setOptionalLookupDefSchema(CFLibDbKeyHash256 argDefSchemaId);
	public void setRequiredContainerFromTable(ICFBamTable argObj);
	public void setRequiredContainerFromTable(CFLibDbKeyHash256 argTableId);
	public void setRequiredLookupFromIndex(ICFBamIndex argObj);
	public void setRequiredLookupFromIndex(CFLibDbKeyHash256 argFromIndexId);
	public void setRequiredLookupToTable(ICFBamTable argObj);
	public void setRequiredLookupToTable(CFLibDbKeyHash256 argToTableId);
	public void setRequiredLookupToIndex(ICFBamIndex argObj);
	public void setRequiredLookupToIndex(CFLibDbKeyHash256 argToIndexId);
	public void setOptionalLookupNarrowed(ICFBamRelation argObj);
	public void setOptionalLookupNarrowed(CFLibDbKeyHash256 argNarrowedId);
	public CFLibDbKeyHash256 getRequiredTableId();
	public CFLibDbKeyHash256 getOptionalDefSchemaId();
	public String getRequiredName();
	public void setRequiredName( String value );
	public String getOptionalShortName();
	public void setOptionalShortName( String value );
	public String getOptionalLabel();
	public void setOptionalLabel( String value );
	public String getOptionalShortDescription();
	public void setOptionalShortDescription( String value );
	public String getOptionalDescription();
	public void setOptionalDescription( String value );
	public ICFBamSchema.RelationTypeEnum getRequiredRelationType();
	public void setRequiredRelationType( ICFBamSchema.RelationTypeEnum value );
	public String getOptionalDbName();
	public void setOptionalDbName( String value );
	public String getOptionalSuffix();
	public void setOptionalSuffix( String value );
	public CFLibDbKeyHash256 getRequiredFromIndexId();
	public CFLibDbKeyHash256 getRequiredToTableId();
	public CFLibDbKeyHash256 getRequiredToIndexId();
	public boolean getRequiredIsRequired();
	public void setRequiredIsRequired( boolean value );
	public boolean getRequiredIsXsdContainer();
	public void setRequiredIsXsdContainer( boolean value );
	public boolean getRequiredIsLateResolver();
	public void setRequiredIsLateResolver( boolean value );
	public boolean getRequiredAllowAddendum();
	public void setRequiredAllowAddendum( boolean value );
	public CFLibDbKeyHash256 getOptionalNarrowedId();
	@Override
	public boolean equals( Object obj );
	
	@Override
	public int hashCode();

	//@Override not necessary because interfaces aren't able to implement Comparable, but they can double-team on the requirement
	public int compareTo( Object obj );

	public void set( ICFBamScope src );
	public void setRelation( ICFBamRelation src );
	public void set( ICFBamScopeH src );
	public void setRelation( ICFBamRelationH src );
}
