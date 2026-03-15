// Description: Java 25 edit object instance implementation for CFBam PopSubDep1.

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

public class CFBamPopSubDep1EditObj
	extends CFBamPopDepEditObj

	implements ICFBamPopSubDep1EditObj
{
	protected ICFBamPopTopDepObj requiredContainerContPopTopDep;
	protected List<ICFBamPopSubDep2Obj> optionalComponentsPopDep;

	public CFBamPopSubDep1EditObj( ICFBamPopSubDep1Obj argOrig ) {
		super( argOrig );
		requiredContainerContPopTopDep = null;
	}

	@Override
	public int getClassCode() {
		return( ((ICFBamSchemaObj)orig.getSchema()).getPopSubDep1TableObj().getClassCode() );
	}

	@Override
	public String getGenDefName() {
		return( "PopSubDep1" );
	}

	@Override
	public ICFLibAnyObj getObjScope() {
		ICFBamPopTopDepObj scope = getRequiredContainerContPopTopDep();
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
		ICFBamPopSubDep1Obj retobj = getSchema().getPopSubDep1TableObj().realisePopSubDep1( (ICFBamPopSubDep1Obj)this );
		return( retobj );
	}

	@Override
	public void forget() {
		getOrigAsPopSubDep1().forget();
	}

	@Override
	public ICFBamScopeObj create() {
		copyRecToOrig();
		ICFBamPopSubDep1Obj retobj = ((ICFBamSchemaObj)getOrigAsPopSubDep1().getSchema()).getPopSubDep1TableObj().createPopSubDep1( getOrigAsPopSubDep1() );
		if( retobj == getOrigAsPopSubDep1() ) {
			copyOrigToRec();
		}
		return( retobj );
	}

	@Override
	public CFBamScopeEditObj update() {
		getSchema().getPopSubDep1TableObj().updatePopSubDep1( (ICFBamPopSubDep1Obj)this );
		return( null );
	}

	@Override
	public CFBamScopeEditObj deleteInstance() {
		if( getIsNew() ) {
			throw new CFLibCannotDeleteNewInstanceException( getClass(), "delete" );
		}
		getSchema().getPopSubDep1TableObj().deletePopSubDep1( getOrigAsPopSubDep1() );
		return( null );
	}

	@Override
	public ICFBamPopSubDep1TableObj getPopSubDep1Table() {
		return( orig.getSchema().getPopSubDep1TableObj() );
	}

	@Override
	public ICFBamPopSubDep1EditObj getEditAsPopSubDep1() {
		return( (ICFBamPopSubDep1EditObj)this );
	}

	@Override
	public ICFBamPopSubDep1Obj getOrigAsPopSubDep1() {
		return( (ICFBamPopSubDep1Obj)orig );
	}

	@Override
	public ICFBamScope getRec() {
		if( rec == null ) {
			rec = getOrigAsPopSubDep1().getSchema().getCFBamBackingStore().getFactoryPopSubDep1().newRec();
			rec.set( orig.getRec() );
		}
		return( rec );
	}

	@Override
	public void setRec( ICFBamScope value ) {
		if( rec != value ) {
			super.setRec( value );
			requiredContainerContPopTopDep = null;
		}
	}

	@Override
	public ICFBamPopSubDep1 getPopSubDep1Rec() {
		return( (ICFBamPopSubDep1)getRec() );
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
	public CFLibDbKeyHash256 getRequiredPopTopDepId() {
		return( getPopSubDep1Rec().getRequiredPopTopDepId() );
	}

	@Override
	public String getRequiredName() {
		return( getPopSubDep1Rec().getRequiredName() );
	}

	@Override
	public void setRequiredName( String value ) {
		if( getPopSubDep1Rec().getRequiredName() != value ) {
			getPopSubDep1Rec().setRequiredName( value );
		}
	}

	@Override
	public ICFBamPopTopDepObj getRequiredContainerContPopTopDep() {
		return( getRequiredContainerContPopTopDep( false ) );
	}

	@Override
	public ICFBamPopTopDepObj getRequiredContainerContPopTopDep( boolean forceRead ) {
		if( forceRead || ( requiredContainerContPopTopDep == null ) ) {
			boolean anyMissing = false;
			if( ! anyMissing ) {
				ICFBamPopTopDepObj obj = ((ICFBamSchemaObj)getOrigAsPopSubDep1().getSchema()).getPopTopDepTableObj().readPopTopDepByIdIdx( getPopSubDep1Rec().getRequiredPopTopDepId() );
				requiredContainerContPopTopDep = obj;
				if( obj != null ) {
					requiredContainerContPopTopDep = obj;
				}
			}
		}
		return( requiredContainerContPopTopDep );
	}

	@Override
	public void setRequiredContainerContPopTopDep( ICFBamPopTopDepObj value ) {
		if( rec == null ) {
			getPopSubDep1Rec();
		}
		if( value != null ) {
			requiredContainerContPopTopDep = value;
			getPopSubDep1Rec().setRequiredContainerContPopTopDep(value.getPopTopDepRec());
		}
		requiredContainerContPopTopDep = value;
	}

	@Override
	public List<ICFBamPopSubDep2Obj> getOptionalComponentsPopDep() {
		List<ICFBamPopSubDep2Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getPopSubDep2TableObj().readPopSubDep2ByPopSubDep1Idx( getPKey(),
			false );
		return( retval );
	}

	@Override
	public List<ICFBamPopSubDep2Obj> getOptionalComponentsPopDep( boolean forceRead ) {
		List<ICFBamPopSubDep2Obj> retval;
		retval = ((ICFBamSchemaObj)getSchema()).getPopSubDep2TableObj().readPopSubDep2ByPopSubDep1Idx( getPKey(),
			forceRead );
		return( retval );
	}

	@Override
	public void copyRecToOrig() {
		ICFBamPopSubDep1 origRec = getOrigAsPopSubDep1().getPopSubDep1Rec();
		ICFBamPopSubDep1 myRec = getPopSubDep1Rec();
		origRec.set( myRec );
	}

	@Override
	public void copyOrigToRec() {
		ICFBamPopSubDep1 origRec = getOrigAsPopSubDep1().getPopSubDep1Rec();
		ICFBamPopSubDep1 myRec = getPopSubDep1Rec();
		myRec.set( origRec );
	}
}
