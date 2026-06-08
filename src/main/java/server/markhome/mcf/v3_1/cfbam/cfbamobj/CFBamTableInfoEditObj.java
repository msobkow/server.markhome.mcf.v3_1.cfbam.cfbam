// Description: Java 25 edit object instance implementation for CFBam TableInfo.

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamTableInfoEditObj
	implements ICFBamTableInfoEditObj
{
	protected ICFSecTableInfoObj orig;
	protected ICFSecTableInfo rec;

	public CFBamTableInfoEditObj( ICFSecTableInfoObj argOrig ) {
		orig = argOrig;
		getRec();
		ICFSecTableInfo origRec = orig.getRec();
		rec.set( origRec );
	}

	@Override
	public int getClassCode() {
		return( ((ICFSecSchemaObj)orig.getSchema()).getTableInfoTableObj().getClassCode() );
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
		// We realise this so that it's record will get copied to orig during realization
		ICFSecTableInfoObj retobj = getSchema().getTableInfoTableObj().realiseTableInfo( (ICFBamTableInfoObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsTableInfo().forget();
	}

	@Override
	public ICFSecTableInfoObj read() {
		ICFSecTableInfoObj retval = getOrigAsTableInfo().read();
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecTableInfoObj read( boolean forceRead ) {
		ICFSecTableInfoObj retval = getOrigAsTableInfo().read( forceRead );
		if( retval != orig ) {
			throw new CFLibStaleCacheDetectedException( getClass(),	"read" );
		}
		copyOrigToRec();
		return( retval );
	}

	@Override
	public ICFSecTableInfoObj create() {
		copyRecToOrig();
		ICFSecTableInfoObj retobj = ((ICFBamSchemaObj)getOrigAsTableInfo().getSchema()).getTableInfoTableObj().createTableInfo( getOrigAsTableInfo() );
		if( retobj == getOrigAsTableInfo() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFSecTableInfoEditObj update() {
		getSchema().getTableInfoTableObj().updateTableInfo( (ICFSecTableInfoObj)this );
		return( null );
	}

	@Override
	public CFSecTableInfoEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getTableInfoTableObj().deleteTableInfo( getOrigAsTableInfo() );
		return( null );
	}

	@Override
	public ICFSecTableInfoTableObj getTableInfoTable() {
		return( orig.getSchema().getTableInfoTableObj() );
	}

	@Override
	public ICFSecTableInfoEditObj getEdit() {
		return( (ICFSecTableInfoEditObj)this );
	}

	@Override
	public ICFSecTableInfoEditObj getEditAsTableInfo() {
		return( (ICFSecTableInfoEditObj)this );
	}

	@Override
	public ICFSecTableInfoEditObj beginEdit() {
		throw new CFLibEditAlreadyOpenException( getClass(), "beginEdit" );
	}

	@Override
	public void endEdit() {
		orig.endEdit();
	}

	@Override
	public ICFSecTableInfoObj getOrig() {
		return( orig );
	}

	@Override
	public ICFSecTableInfoObj getOrigAsTableInfo() {
		return( (ICFSecTableInfoObj)orig );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( orig.getSchema() );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		orig.setSchema(value);
	}

	@Override
	public ICFSecTableInfo getRec() {
		if( rec == null ) {
			rec = getOrigAsTableInfo().getSchema().getCFSecBackingStore().getFactoryTableInfo().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFSecTableInfo value ) {
		if( rec != value ) {
			rec = value;
		}
	}

	@Override
	public ICFSecTableInfo getTableInfoRec() {
		return( (ICFSecTableInfo)getRec() );
	}

	@Override
	public Integer getPKey() {
		return( orig.getPKey() );
	}

	@Override
	public void setPKey( Integer value ) {
		orig.setPKey( value );
		copyPKeyToRec();
	}

	@Override
	public boolean getIsNew() {
		return( orig.getIsNew() );
	}

	@Override
	public void setIsNew( boolean value ) {
		orig.setIsNew( value );
	}

	@Override
	public int getRequiredTableInfoId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredTableInfoId(int value) {
		if (getPKey() != value) {
			setPKey(value);
		}
	}

	@Override
	public String getRequiredSchemaName() {
		return( getTableInfoRec().getRequiredSchemaName() );
	}

	@Override
	public void setRequiredSchemaName( String value ) {
		if( getTableInfoRec().getRequiredSchemaName() != value ) {
			getTableInfoRec().setRequiredSchemaName( value );
		}
	}

	@Override
	public String getRequiredTableName() {
		return( getTableInfoRec().getRequiredTableName() );
	}

	@Override
	public void setRequiredTableName( String value ) {
		if( getTableInfoRec().getRequiredTableName() != value ) {
			getTableInfoRec().setRequiredTableName( value );
		}
	}

	@Override
	public int getRequiredBackingClassCode() {
		return( getTableInfoRec().getRequiredBackingClassCode() );
	}

	@Override
	public void setRequiredBackingClassCode( int value ) {
		if( getTableInfoRec().getRequiredBackingClassCode() != value ) {
			getTableInfoRec().setRequiredBackingClassCode( value );
		}
	}

	@Override
	public int getRequiredRuntimeClassCode() {
		return( getTableInfoRec().getRequiredRuntimeClassCode() );
	}

	@Override
	public void setRequiredRuntimeClassCode( int value ) {
		if( getTableInfoRec().getRequiredRuntimeClassCode() != value ) {
			getTableInfoRec().setRequiredRuntimeClassCode( value );
		}
	}

	@Override
	public boolean getRequiredHasHistory() {
		return( getTableInfoRec().getRequiredHasHistory() );
	}

	@Override
	public void setRequiredHasHistory( boolean value ) {
		if( getTableInfoRec().getRequiredHasHistory() != value ) {
			getTableInfoRec().setRequiredHasHistory( value );
		}
	}

	@Override
	public boolean getRequiredIsMutable() {
		return( getTableInfoRec().getRequiredIsMutable() );
	}

	@Override
	public void setRequiredIsMutable( boolean value ) {
		if( getTableInfoRec().getRequiredIsMutable() != value ) {
			getTableInfoRec().setRequiredIsMutable( value );
		}
	}

	@Override
	public String getRequiredSecScopeName() {
		return( getTableInfoRec().getRequiredSecScopeName() );
	}

	@Override
	public void setRequiredSecScopeName( String value ) {
		if( getTableInfoRec().getRequiredSecScopeName() != value ) {
			getTableInfoRec().setRequiredSecScopeName( value );
		}
	}

	@Override
	public String getRequiredCodeVis() {
		return( getTableInfoRec().getRequiredCodeVis() );
	}

	@Override
	public void setRequiredCodeVis( String value ) {
		if( getTableInfoRec().getRequiredCodeVis() != value ) {
			getTableInfoRec().setRequiredCodeVis( value );
		}
	}

	@Override
	public void copyPKeyToRec() {
		if( rec != null ) {
			if (getPKey() != rec.getPKey()) {
				rec.setPKey(getPKey());
			}
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

	@Override
	public void copyRecToOrig() {
		ICFSecTableInfo origRec = getOrigAsTableInfo().getTableInfoRec();
		ICFSecTableInfo myRec = getTableInfoRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFSecTableInfo origRec = getOrigAsTableInfo().getTableInfoRec();
		ICFSecTableInfo myRec = getTableInfoRec();
		myRec.set( origRec );
	}
}
