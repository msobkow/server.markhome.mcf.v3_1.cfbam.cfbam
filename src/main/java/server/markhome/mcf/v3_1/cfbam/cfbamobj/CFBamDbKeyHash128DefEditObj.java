// Description: Java 25 edit object instance implementation for CFBam DbKeyHash128Def.

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

public class CFBamDbKeyHash128DefEditObj
	extends CFBamAtomEditObj

	implements ICFBamDbKeyHash128DefEditObj
{

	public CFBamDbKeyHash128DefEditObj( ICFBamDbKeyHash128DefObj argOrig ) {
		super( argOrig );
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getDbKeyHash128DefTableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "DbKeyHash128Def" );
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
		ICFBamDbKeyHash128DefObj retobj = getSchema().getDbKeyHash128DefTableObj().realiseDbKeyHash128Def( (ICFBamDbKeyHash128DefObj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsDbKeyHash128Def().forget();
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
		ICFBamDbKeyHash128DefObj retobj = ((ICFBamSchemaObj)getOrigAsDbKeyHash128Def().getSchema()).getDbKeyHash128DefTableObj().createDbKeyHash128Def( getOrigAsDbKeyHash128Def() );
		if( retobj == getOrigAsDbKeyHash128Def() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamValueEditObj update() {
		getSchema().getDbKeyHash128DefTableObj().updateDbKeyHash128Def( (ICFBamDbKeyHash128DefObj)this );
		return( null );
	}

	@Override
	public CFBamValueEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getDbKeyHash128DefTableObj().deleteDbKeyHash128Def( getOrigAsDbKeyHash128Def() );
		return( null );
	}

	@Override
	public ICFBamDbKeyHash128DefTableObj getDbKeyHash128DefTable() {
		return( orig.getSchema().getDbKeyHash128DefTableObj() );
	}

	@Override
	public ICFBamDbKeyHash128DefEditObj getEditAsDbKeyHash128Def() {
		return( (ICFBamDbKeyHash128DefEditObj)this );
	}

	@Override
	public ICFBamDbKeyHash128DefObj getOrigAsDbKeyHash128Def() {
		return( (ICFBamDbKeyHash128DefObj)orig );
	}

	@Override
	public ICFBamValue getRec() {
		if( rec == null ) {
			rec = getOrigAsDbKeyHash128Def().getSchema().getCFBamBackingStore().getFactoryDbKeyHash128Def().newRec();
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
	public ICFBamDbKeyHash128Def getDbKeyHash128DefRec() {
		return( (ICFBamDbKeyHash128Def)getRec() );
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
	public String getOptionalInitValue() {
		return( getDbKeyHash128DefRec().getOptionalInitValue() );
	}

	@Override
	public void setOptionalInitValue( String value ) {
		if( getDbKeyHash128DefRec().getOptionalInitValue() != value ) {
			getDbKeyHash128DefRec().setOptionalInitValue( value );
		}
	}

	@Override
	public void copyRecToOrig() {
		ICFBamDbKeyHash128Def origRec = getOrigAsDbKeyHash128Def().getDbKeyHash128DefRec();
		ICFBamDbKeyHash128Def myRec = getDbKeyHash128DefRec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamDbKeyHash128Def origRec = getOrigAsDbKeyHash128Def().getDbKeyHash128DefRec();
		ICFBamDbKeyHash128Def myRec = getDbKeyHash128DefRec();
		myRec.set( origRec );
	}
}
