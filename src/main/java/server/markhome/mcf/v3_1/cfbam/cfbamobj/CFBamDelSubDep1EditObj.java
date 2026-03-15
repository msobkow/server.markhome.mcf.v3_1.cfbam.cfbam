// Description: Java 25 edit object instance implementation for CFBam DelSubDep1.

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

public class CFBamDelSubDep1EditObj
	extends CFBamDelDepEditObj

	implements ICFBamDelSubDep1EditObj
{
	protected ICFBamDelTopDepObj requiredContainerDelTopDep;
	protected List<ICFBamDelSubDep2Obj> optionalComponentsDelDep;

	public CFBamDelSubDep1EditObj( ICFBamDelSubDep1Obj argOrig ) {
		super( argOrig );
		requiredContainerDelTopDep = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getDelSubDep1TableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "DelSubDep1" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamDelTopDepObj scope = getRequiredContainerDelTopDep();
		return( scope );
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
	public ICFBamScopeObj realise() {
		// We realise this so that it's record will get copied to orig during realization
		ICFBamDelSubDep1Obj retobj = getSchema().getDelSubDep1TableObj().realiseDelSubDep1( (ICFBamDelSubDep1Obj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsDelSubDep1().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamDelSubDep1Obj retobj = ((ICFBamSchemaObj)getOrigAsDelSubDep1().getSchema()).getDelSubDep1TableObj().createDelSubDep1( getOrigAsDelSubDep1() );
		if( retobj == getOrigAsDelSubDep1() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getDelSubDep1TableObj().updateDelSubDep1( (ICFBamDelSubDep1Obj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getDelSubDep1TableObj().deleteDelSubDep1( getOrigAsDelSubDep1() );
		return( null );
	}

	@Override
	public ICFBamDelSubDep1TableObj getDelSubDep1Table() {
		return( orig.getSchema().getDelSubDep1TableObj() );
	}

	@Override
	public ICFBamDelSubDep1EditObj getEditAsDelSubDep1() {
		return( (ICFBamDelSubDep1EditObj)this );
	}

	@Override
	public ICFBamDelSubDep1Obj getOrigAsDelSubDep1() {
		return( (ICFBamDelSubDep1Obj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsDelSubDep1().getSchema().getCFBamBackingStore().getFactoryDelSubDep1().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerDelTopDep = null;
		}
	}

	@Override
	public ICFBamDelSubDep1 getDelSubDep1Rec() {
		return( (ICFBamDelSubDep1)getRec() );
	}

	@Override
	public CFLibDbKeyHash256 getRequiredId() {
		return( getPKey() );
	}

	@Override
	public void setRequiredId(CFLibDbKeyHash256 id) {
		if (getPKey() != id) {
			setPKey(id);
		}
	}

	@Override
	public CFLibDbKeyHash256 getRequiredDelTopDepId() {
		return( getDelSubDep1Rec().getRequiredDelTopDepId() );
	}

	@Override
	public String getRequiredName() {
		return( getDelSubDep1Rec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getDelSubDep1Rec().getRequiredName() != value ) {
			getDelSubDep1Rec().setRequiredName( value );
		}
	}

	@Override
	public ICFBamDelTopDepObj getRequiredContainerDelTopDep() {
		return( getRequiredContainerDelTopDep( false ) );
	}

	@Override
	public ICFBamDelTopDepObj getRequiredContainerDelTopDep( boolean forceRead ) {
		if( forceRead || ( requiredContainerDelTopDep == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamDelTopDepObj obj = ((ICFBamSchemaObj)getOrigAsDelSubDep1().getSchema()).getDelTopDepTableObj().readDelTopDepByIdIdx( getDelSubDep1Rec().getRequiredDelTopDepId() );
				requiredContainerDelTopDep = obj;
				if( obj != null ) {
					requiredContainerDelTopDep = obj;
				}
			}
		}
		return( requiredContainerDelTopDep );
	}

	@Override
	public void setRequiredContainerDelTopDep( ICFBamDelTopDepObj value ) {
		if( rec == null ) {
			getDelSubDep1Rec();
		}
		if( value != null ) {
			requiredContainerDelTopDep = value;
			getDelSubDep1Rec().setRequiredContainerDelTopDep(value.getDelTopDepRec());
		}
		requiredContainerDelTopDep = value;
	}

	@Override
	public List<ICFBamDelSubDep2Obj> getOptionalComponentsDelDep() {
		List<ICFBamDelSubDep2Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().readDelSubDep2ByContDelDep1Idx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamDelSubDep2Obj> getOptionalComponentsDelDep( boolean forceRead ) {
		List<ICFBamDelSubDep2Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getDelSubDep2TableObj().readDelSubDep2ByContDelDep1Idx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamDelSubDep1 origRec = getOrigAsDelSubDep1().getDelSubDep1Rec();
		ICFBamDelSubDep1 myRec = getDelSubDep1Rec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamDelSubDep1 origRec = getOrigAsDelSubDep1().getDelSubDep1Rec();
		ICFBamDelSubDep1 myRec = getDelSubDep1Rec();
		myRec.set( origRec );
	}
}
