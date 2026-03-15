// Description: Java 25 edit object instance implementation for CFBam Uuid6Gen.

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
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;

public class CFBamUuid6GenEditObj
	extends CFBamUuid6TypeEditObj

	implements ICFBamUuid6GenEditObj
{

	public CFBamUuid6GenEditObj( ICFBamUuid6GenObj argOrig ) {
		super( argOrig );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getUuid6GenTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "Uuid6Gen" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		return( super.getObjScope() );
	}

	@Override
	public String getObjName() {
		String objName;
		objName = getRequiredName();
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
			else if( container instanceof ICFBamSchemaDefObj ) {
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
	public ICFBamValueObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamUuid6GenObj retobj = getSchema().getUuid6GenTableObj().realiseUuid6Gen( (ICFBamUuid6GenObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsUuid6Gen().forget();
	}

	@Override
	public ICFBamValueObj moveUp() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveUp" );
	}

	@Override
	public ICFBamValueObj moveDown() {
		throw new CFLibCannotMoveEditedObjectException( getClass(),	"moveDown" );
	}

	@Override
	public ICFBamValueObj create() {
		copyRecToOrig();
		ICFBamUuid6GenObj retobj = ((ICFBamSchemaObj)getOrigAsUuid6Gen().getSchema()).getUuid6GenTableObj().createUuid6Gen( getOrigAsUuid6Gen() );
		if( retobj == getOrigAsUuid6Gen() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamValueEditObj update() {
		getSchema().getUuid6GenTableObj().updateUuid6Gen( (ICFBamUuid6GenObj)this );
		return( null );
	}

	@Override
	public CFBamValueEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getUuid6GenTableObj().deleteUuid6Gen( getOrigAsUuid6Gen() );
		return( null );
	}

	@Override
	public ICFBamUuid6GenTableObj getUuid6GenTable() {
		return( orig.getSchema().getUuid6GenTableObj() );
	}

	@Override
	public ICFBamUuid6GenEditObj getEditAsUuid6Gen() {
		return( (ICFBamUuid6GenEditObj)this );
	}

	@Override
	public ICFBamUuid6GenObj getOrigAsUuid6Gen() {
		return( (ICFBamUuid6GenObj)orig );
	}

	@Override
	public ICFBamValue getRec() {
		if( rec == null ) {
			rec = getOrigAsUuid6Gen().getSchema().getCFBamBackingStore().getFactoryUuid6Gen().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamValue value ) {
		if( rec != value ) {
			super.setRec( value );
		}
	}

	@Override
	public ICFBamUuid6Gen getUuid6GenRec() {
		return( (ICFBamUuid6Gen)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
			requiredContainerScope = null;
			optionalLookupDefSchema = null;
			optionalChildrenRefTableCol = null;
			optionalChildrenRefIndexCol = null;
			optionalLookupPrev = null;
			optionalLookupNext = null;
		}
	}

	@Override
	public short getRequiredSlice() {
		return( getUuid6GenRec().getRequiredSlice() );
	}

	@Override
	public void setRequiredSlice( short value ) {
		if( getUuid6GenRec().getRequiredSlice() != value ) {
			getUuid6GenRec().setRequiredSlice( value );
		}
	}

	@Override
	public int getRequiredBlockSize() {
		return( getUuid6GenRec().getRequiredBlockSize() );
	}

	@Override
	public void setRequiredBlockSize( int value ) {
		if( getUuid6GenRec().getRequiredBlockSize() != value ) {
			getUuid6GenRec().setRequiredBlockSize( value );
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFBamUuid6Gen origRec = getOrigAsUuid6Gen().getUuid6GenRec();
		ICFBamUuid6Gen myRec = getUuid6GenRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamUuid6Gen origRec = getOrigAsUuid6Gen().getUuid6GenRec();
		ICFBamUuid6Gen myRec = getUuid6GenRec();
		myRec.set( origRec );
	}
}
