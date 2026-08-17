// Description: Java 25 base object instance implementation for TableInfo

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
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
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfint.cfintpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpub.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;
import server.markhome.mcf.v3_1.cfint.cfintpubobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecpubobj.*;

public class CFBamTableInfoObj
	implements ICFBamTableInfoObj
{
	protected boolean isNew;
	protected ICFSecTableInfoEditObj edit;
	protected ICFSecSchemaObj schema;
	protected Integer pKey;
	protected ICFSecTableInfo rec;
	protected ICFSecTableInfoObj optionalParentSuperRef;
	protected List<ICFSecTableInfoObj> optionalChildrenSubRefs;

	public CFBamTableInfoObj() {
		isNew = true;
		optionalParentSuperRef = null;
	}

	public CFBamTableInfoObj( ICFSecSchemaObj argSchema ) {
		schema = argSchema;
		isNew = true;
		edit = null;
		optionalParentSuperRef = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)schema).getTableInfoTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "TableInfo" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( null );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredTableName();
		return( objName );
	}

	@Override
	public ICFLibAnyObj getObjQualifier( Class qualifyingClass ) {
		ICFLibAnyObj container = this;
		if( qualifyingClass != null ) {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				else if( qualifyingClass.isInstance( container ) ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		else {
			while( container != null ) {
				if( container instanceof ICFBamClusterObj ) {
					break;
				}
				else if( container instanceof ICFBamTenantObj ) {
					break;
				}
				container = container.getObjScope();
			}
		}
		return( container );
	}

	@Override
	public ICFLibAnyObj getNamedObject( Class qualifyingClass, String objName ) {
		ICFLibAnyObj topContainer = getObjQualifier( qualifyingClass );
		if( topContainer == null ) {
			return( null );
		}
		ICFLibAnyObj namedObject = topContainer.getNamedObject( objName );
		return( namedObject );
	}

	@Override
	public ICFLibAnyObj getNamedObject( String objName ) {
		String nextName;
		String remainingName;
		ICFLibAnyObj subObj = null;
		ICFLibAnyObj retObj;
		int nextDot = objName.indexOf( '.' );
		if( nextDot >= 0 ) {
			nextName = objName.substring( 0, nextDot );
			remainingName = objName.substring( nextDot + 1 );
		}
		else {
			nextName = objName;
			remainingName = null;
		}
		if( remainingName == null ) {
			retObj = subObj;
		}
		else if( subObj == null ) {
			retObj = null;
		}
		else {
			retObj = subObj.getNamedObject( remainingName );
		}
		return( retObj );
	}

	@Override
	public String getObjQualifiedName() {
		String qualName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				qualName = containerName + "." + qualName;
				container = container.getObjScope();
			}
		}
		return( qualName );
	}

	@Override
	public String getObjFullName() {
		String fullName = getObjName();
		ICFLibAnyObj container = getObjScope();
		String containerName;
		while( container != null ) {
			if( container instanceof ICFSecClusterObj ) {
				container = null;
			}
			else if( container instanceof ICFSecTenantObj ) {
				container = null;
			}
			else {
				containerName = container.getObjName();
				fullName = containerName + "." + fullName;
				container = container.getObjScope();
			}
		}
		return( fullName );
	}

	@Override
	public ICFSecTableInfoObj realise() {
		ICFSecTableInfoObj retobj = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().realiseTableInfo(
			(ICFSecTableInfoObj)this );
		return( (ICFSecTableInfoObj)retobj );
	}

	@Override
	public void forget() {
		((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().reallyDeepDisposeTableInfo( (ICFSecTableInfoObj)this );
	}

	@Override
	public ICFSecTableInfoObj read() {
		ICFSecTableInfoObj retobj = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoByIdIdx( getPKey(), false );
		return( (ICFSecTableInfoObj)retobj );
	}

	@Override
	public ICFSecTableInfoObj read( boolean forceRead ) {
		ICFSecTableInfoObj retobj = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoByIdIdx( getPKey(), forceRead );
		return( (ICFSecTableInfoObj)retobj );
	}

	@Override
	public ICFSecTableInfoTableObj getTableInfoTable() {
		return( ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = value;
	}

	@Override
	public ICFSecTableInfo getRec() {
		if( rec == null ) {
			if( isNew ) {
				rec = getSchema().getCFSecBackingStore().getCFSecFactory().getFactoryTableInfo().newRec();
			}
			else {
				// Read the data rec via the backing store
				rec = getSchema().getCFSecBackingStore().getTableTableInfo().readDerivedByIdIdx( ((ICFBamSchemaObj)getSchema()).getAuthorization(),
						getPKey() );
				if( rec != null ) {
					copyRecToPKey();
				}
			}
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecTableInfo value ) {
		if( ! ( ( value == null ) || ! ( value instanceof ICFSecTableInfo ) ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				"setRec",
				"value",
				value,
				"CFSecTableInfoRec" );
		}
		rec = value;
		copyRecToPKey();
		optionalParentSuperRef = null;
	}

	@Override
	public ICFSecTableInfo getTableInfoRec() {
		return( (ICFSecTableInfo)getRec() );
	}

	@Override
	public Integer getPKey() {
		return( pKey );
	}

	@Override
	public void setPKey( Integer value ) {
		if( pKey != value ) {
       		pKey = value;
			copyPKeyToRec();
		}
	}

	@Override
	public boolean getIsNew() {
		return( isNew );
	}

	@Override
	public void setIsNew( boolean value ) {
		isNew = value;
	}

	@Override
	public ICFSecTableInfoEditObj beginEdit() {
		if( edit != null ) {
			throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
		}
		ICFSecTableInfoObj lockobj;
		if( getIsNew() ) {
			lockobj = (ICFSecTableInfoObj)this;
		}
		else {
			lockobj = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().lockTableInfo( getPKey() );
		}
		edit = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().newEditInstance( lockobj );
		return( (ICFSecTableInfoEditObj)edit );
	}

	@Override
	public void endEdit() {
		edit = null;
	}

	@Override
	public ICFSecTableInfoEditObj getEdit() {
		return( edit );
	}

	@Override
	public ICFSecTableInfoEditObj getEditAsTableInfo() {
		return( (ICFSecTableInfoEditObj)edit );
	}

	@Override
	public int getRequiredTableInfoId() {
		return( getPKey() );
	}

	@Override
	public ICFSecTableInfoObj getOptionalParentSuperRef() {
		return( getOptionalParentSuperRef( false ) );
	}

	@Override
	public ICFSecTableInfoObj getOptionalParentSuperRef( boolean forceRead ) {
		if( ( optionalParentSuperRef == null ) || forceRead ) {
			boolean anyMissing = false;
			if( getTableInfoRec().getOptionalSuperName() == null ) {
				anyMissing = true;
			}
			if( ! anyMissing ) {
				optionalParentSuperRef = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoByTableNameIdx( getTableInfoRec().getOptionalSuperName(), forceRead );
			}
		}
		return( optionalParentSuperRef );
	}

	@Override
	public List<ICFSecTableInfoObj> getOptionalChildrenSubRefs() {
		List<ICFSecTableInfoObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoBySuperNameIdx( getTableInfoRec().getRequiredTableName(),
			false );
		return( retval );
	}

	@Override
	public List<ICFSecTableInfoObj> getOptionalChildrenSubRefs( boolean forceRead ) {
		List<ICFSecTableInfoObj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getTableInfoTableObj().readTableInfoBySuperNameIdx( getTableInfoRec().getRequiredTableName(),
			forceRead );
		return( retval );
	}

	@Override
	public String getRequiredSchemaName() {
		return( getTableInfoRec().getRequiredSchemaName() );
	}

	@Override
	public String getRequiredTableName() {
		return( getTableInfoRec().getRequiredTableName() );
	}

	@Override
	public String getOptionalSuperName() {
		return( getTableInfoRec().getOptionalSuperName() );
	}

	@Override
	public int getRequiredBackingClassCode() {
		return( getTableInfoRec().getRequiredBackingClassCode() );
	}

	@Override
	public int getRequiredRuntimeClassCode() {
		return( getTableInfoRec().getRequiredRuntimeClassCode() );
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( getTableInfoRec().getRequiredHasHistory() );
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( getTableInfoRec().getRequiredIsMutable() );
	}

	@Override
	public String getRequiredSecScopeName() {
		return( getTableInfoRec().getRequiredSecScopeName() );
	}

	@Override
	public String getRequiredCodeVis() {
		return( getTableInfoRec().getRequiredCodeVis() );
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
		}
		if( edit != null ) {
			edit.copyPKeyToRec();
		}
	}

	@Override
	public void copyRecToPKey() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				setPKey(rec.getPKey());
			}
		}
	}
}
