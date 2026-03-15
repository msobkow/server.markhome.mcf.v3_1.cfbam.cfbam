// Description: Java 25 Table Object implementation for ClearSubDep1.

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

public class CFBamClearSubDep1TableObj
	implements ICFBamClearSubDep1TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamClearSubDep1.CLASS_CODE;
	protected static final int backingClassCode = ICFBamClearSubDep1.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> allClearSubDep1;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > > indexByTenantIdx;
	private Map< ICFBamClearDepByClearDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > > indexByClearDepIdx;
	private Map< ICFBamClearDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > > indexByDefSchemaIdx;
	private Map< ICFBamClearSubDep1ByClearTopDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > > indexByClearTopDepIdx;
	private Map< ICFBamClearSubDep1ByUNameIdxKey,
		ICFBamClearSubDep1Obj > indexByUNameIdx;
	public static String TABLE_NAME = "ClearSubDep1";
	public static String TABLE_DBNAME = "clrsubdep1";

	public CFBamClearSubDep1TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep1Obj>();
		allClearSubDep1 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearTopDepIdx = null;
		indexByUNameIdx = null;
	}

	public CFBamClearSubDep1TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep1Obj>();
		allClearSubDep1 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearTopDepIdx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamClearSubDep1TableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( backingClassCode );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( runtimeClassCode );
	}

	/**
	 *	Set the runtime class code for this table; this is done only during application initialization by the SchemaObj's <tt>initClassCodes()</tt> static method,
	 *	which will only set the class codes once and never again.  Once set, the class codes are immutable within the application.
	 *	Application programmers should never invoke this method, so it has package access only.
	 *
	 *	@param	argNewClassCode	The runtime class code to be used by clients and integrated application logic to identify this table of this schema.
	 */
	static void setRuntimeClassCode(int argNewClassCode ) {
		if (argNewClassCode <= 0) {
			throw new CFLibArgumentUnderflowException(CFBamClearSubDep1TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
		}
		runtimeClassCode = argNewClassCode;
	}

	@Override
	public ICFBamSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFBamSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( ICFBamSchemaDefObj.class );
	}


	@Override
	public void minimizeMemory() {
		allClearSubDep1 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearTopDepIdx = null;
		indexByUNameIdx = null;
		List<ICFBamClearSubDep1Obj> toForget = new LinkedList<ICFBamClearSubDep1Obj>();
		ICFBamClearSubDep1Obj cur = null;
		Iterator<ICFBamClearSubDep1Obj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamClearSubDep1Obj.
	 */
	@Override
	public ICFBamClearSubDep1Obj newInstance() {
		ICFBamClearSubDep1Obj inst = new CFBamClearSubDep1Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamClearSubDep1Obj.
	 */
	@Override
	public ICFBamClearSubDep1EditObj newEditInstance( ICFBamClearSubDep1Obj orig ) {
		ICFBamClearSubDep1EditObj edit = new CFBamClearSubDep1EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamClearSubDep1Obj realiseClearSubDep1( ICFBamClearSubDep1Obj Obj ) {
		ICFBamClearSubDep1Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearSubDep1Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamClearSubDep1Obj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					indexByClearDepIdx.remove( keyClearDepIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByClearTopDepIdx != null ) {
				ICFBamClearSubDep1ByClearTopDepIdxKey keyClearTopDepIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
				keyClearTopDepIdx.setRequiredClearTopDepId( keepObj.getRequiredClearTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapClearTopDepIdx = indexByClearTopDepIdx.get( keyClearTopDepIdx );
				if( mapClearTopDepIdx != null ) {
					mapClearTopDepIdx.remove( keepObj.getPKey() );
					if( mapClearTopDepIdx.size() <= 0 ) {
						indexByClearTopDepIdx.remove( keyClearTopDepIdx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearTopDepId( keepObj.getRequiredClearTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamClearSubDep1Obj)schema.getClearDepTableObj().realiseClearDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearTopDepIdx != null ) {
				ICFBamClearSubDep1ByClearTopDepIdxKey keyClearTopDepIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
				keyClearTopDepIdx.setRequiredClearTopDepId( keepObj.getRequiredClearTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapClearTopDepIdx = indexByClearTopDepIdx.get( keyClearTopDepIdx );
				if( mapClearTopDepIdx != null ) {
					mapClearTopDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearTopDepId( keepObj.getRequiredClearTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allClearSubDep1 != null ) {
				allClearSubDep1.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamClearSubDep1Obj)schema.getClearDepTableObj().realiseClearDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allClearSubDep1 != null ) {
				allClearSubDep1.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearTopDepIdx != null ) {
				ICFBamClearSubDep1ByClearTopDepIdxKey keyClearTopDepIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
				keyClearTopDepIdx.setRequiredClearTopDepId( keepObj.getRequiredClearTopDepId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj > mapClearTopDepIdx = indexByClearTopDepIdx.get( keyClearTopDepIdx );
				if( mapClearTopDepIdx != null ) {
					mapClearTopDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep1ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearTopDepId( keepObj.getRequiredClearTopDepId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamClearSubDep1Obj createClearSubDep1( ICFBamClearSubDep1Obj Obj ) {
		ICFBamClearSubDep1Obj obj = Obj;
		ICFBamClearSubDep1 rec = obj.getClearSubDep1Rec();
		schema.getCFBamBackingStore().getTableClearSubDep1().createClearSubDep1(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamClearSubDep1Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamClearSubDep1Obj readClearSubDep1( CFLibDbKeyHash256 pkey ) {
		return( readClearSubDep1( pkey, false ) );
	}

	@Override
	public ICFBamClearSubDep1Obj readClearSubDep1( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamClearSubDep1Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamClearSubDep1 readRec = schema.getCFBamBackingStore().getTableClearSubDep1().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamClearSubDep1Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearSubDep1Obj readCachedClearSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep1Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeClearSubDep1( ICFBamClearSubDep1Obj obj )
	{
		final String S_ProcName = "CFBamClearSubDep1TableObj.reallyDeepDisposeClearSubDep1() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearSubDep1Obj existing = readCachedClearSubDep1( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamClearSubDep1ByClearTopDepIdxKey keyClearTopDepIdx = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
		keyClearTopDepIdx.setRequiredClearTopDepId( existing.getRequiredClearTopDepId() );

		ICFBamClearSubDep1ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
		keyUNameIdx.setRequiredClearTopDepId( existing.getRequiredClearTopDepId() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getClearSubDep2TableObj().deepDisposeClearSubDep2ByClearSubDep1Idx( existing.getRequiredId() );

		if( indexByClearTopDepIdx != null ) {
			if( indexByClearTopDepIdx.containsKey( keyClearTopDepIdx ) ) {
				indexByClearTopDepIdx.get( keyClearTopDepIdx ).remove( pkey );
				if( indexByClearTopDepIdx.get( keyClearTopDepIdx ).size() <= 0 ) {
					indexByClearTopDepIdx.remove( keyClearTopDepIdx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getClearDepTableObj().reallyDeepDisposeClearDep( obj );
	}
	@Override
	public void deepDisposeClearSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep1Obj obj = readCachedClearSubDep1( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearSubDep1Obj lockClearSubDep1( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep1Obj locked = null;
		ICFBamClearSubDep1 lockRec = schema.getCFBamBackingStore().getTableClearSubDep1().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamClearSubDep1Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockClearSubDep1", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readAllClearSubDep1() {
		return( readAllClearSubDep1( false ) );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readAllClearSubDep1( boolean forceRead ) {
		final String S_ProcName = "readAllClearSubDep1";
		if( ( allClearSubDep1 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamClearSubDep1Obj>();
			allClearSubDep1 = map;
			ICFBamClearSubDep1[] recList = schema.getCFBamBackingStore().getTableClearSubDep1().readAllDerived( null );
			ICFBamClearSubDep1 rec;
			ICFBamClearSubDep1Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep1Obj realised = (ICFBamClearSubDep1Obj)obj.realise();
			}
		}
		int len = allClearSubDep1.size();
		ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
		Iterator<ICFBamClearSubDep1Obj> valIter = allClearSubDep1.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamClearSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readCachedAllClearSubDep1() {
		final String S_ProcName = "readCachedAllClearSubDep1";
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>();
		if( allClearSubDep1 != null ) {
			int len = allClearSubDep1.size();
			ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
			Iterator<ICFBamClearSubDep1Obj> valIter = allClearSubDep1.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamClearSubDep1Obj readClearSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readClearSubDep1ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamClearSubDep1Obj readClearSubDep1ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamClearSubDep1Obj obj = readClearSubDep1( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readClearSubDep1ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep1ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep1Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep1Obj realised = (ICFBamClearSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
		Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamClearSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readClearSubDep1ByClearDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByClearDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep1ByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByClearDepIdx.containsKey( key ) ) {
			dict = indexByClearDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep1Obj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByClearDepIdx( null,
				RelationId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep1Obj realised = (ICFBamClearSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
		Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamClearSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readClearSubDep1ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep1ByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep1Obj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep1Obj realised = (ICFBamClearSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
		Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamClearSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByClearTopDepIdx( CFLibDbKeyHash256 ClearTopDepId )
	{
		return( readClearSubDep1ByClearTopDepIdx( ClearTopDepId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readClearSubDep1ByClearTopDepIdx( CFLibDbKeyHash256 ClearTopDepId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep1ByClearTopDepIdx";
		ICFBamClearSubDep1ByClearTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
		key.setRequiredClearTopDepId( ClearTopDepId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
		if( indexByClearTopDepIdx == null ) {
			indexByClearTopDepIdx = new HashMap< ICFBamClearSubDep1ByClearTopDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( ( ! forceRead ) && indexByClearTopDepIdx.containsKey( key ) ) {
			dict = indexByClearTopDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep1Obj>();
			ICFBamClearSubDep1Obj obj;
			ICFBamClearSubDep1[] recList = schema.getCFBamBackingStore().getTableClearSubDep1().readDerivedByClearTopDepIdx( null,
				ClearTopDepId );
			ICFBamClearSubDep1 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep1Obj realised = (ICFBamClearSubDep1Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearTopDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
		Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFBamClearSubDep1Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamClearSubDep1Obj readClearSubDep1ByUNameIdx( CFLibDbKeyHash256 ClearTopDepId,
		String Name )
	{
		return( readClearSubDep1ByUNameIdx( ClearTopDepId,
			Name,
			false ) );
	}

	@Override
	public ICFBamClearSubDep1Obj readClearSubDep1ByUNameIdx( CFLibDbKeyHash256 ClearTopDepId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearSubDep1ByUNameIdxKey,
				ICFBamClearSubDep1Obj >();
		}
		ICFBamClearSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
		key.setRequiredClearTopDepId( ClearTopDepId );
		key.setRequiredName( Name );
		ICFBamClearSubDep1Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamClearSubDep1 rec = schema.getCFBamBackingStore().getTableClearSubDep1().readDerivedByUNameIdx( null,
				ClearTopDepId,
				Name );
			if( rec != null ) {
				obj = (ICFBamClearSubDep1Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamClearSubDep1Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearSubDep1Obj readCachedClearSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep1Obj obj = null;
		obj = readCachedClearSubDep1( Id );
		return( obj );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readCachedClearSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedClearSubDep1ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
				Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamClearSubDep1Obj obj;
			Iterator<ICFBamClearSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readCachedClearSubDep1ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedClearSubDep1ByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>();
		if( indexByClearDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
			if( indexByClearDepIdx.containsKey( key ) ) {
				dict = indexByClearDepIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
				Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamClearSubDep1Obj obj;
			Iterator<ICFBamClearSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readCachedClearSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedClearSubDep1ByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
				Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamClearSubDep1Obj obj;
			Iterator<ICFBamClearSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFBamClearSubDep1Obj> readCachedClearSubDep1ByClearTopDepIdx( CFLibDbKeyHash256 ClearTopDepId )
	{
		final String S_ProcName = "readCachedClearSubDep1ByClearTopDepIdx";
		ICFBamClearSubDep1ByClearTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
		key.setRequiredClearTopDepId( ClearTopDepId );
		ArrayList<ICFBamClearSubDep1Obj> arrayList = new ArrayList<ICFBamClearSubDep1Obj>();
		if( indexByClearTopDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict;
			if( indexByClearTopDepIdx.containsKey( key ) ) {
				dict = indexByClearTopDepIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep1Obj arr[] = new ICFBamClearSubDep1Obj[len];
				Iterator<ICFBamClearSubDep1Obj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFBamClearSubDep1Obj obj;
			Iterator<ICFBamClearSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep1Obj> cmp = new Comparator<ICFBamClearSubDep1Obj>() {
			@Override
			public int compare( ICFBamClearSubDep1Obj lhs, ICFBamClearSubDep1Obj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public ICFBamClearSubDep1Obj readCachedClearSubDep1ByUNameIdx( CFLibDbKeyHash256 ClearTopDepId,
		String Name )
	{
		ICFBamClearSubDep1Obj obj = null;
		ICFBamClearSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
		key.setRequiredClearTopDepId( ClearTopDepId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamClearSubDep1Obj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFBamClearSubDep1Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public void deepDisposeClearSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep1Obj obj = readCachedClearSubDep1ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeClearSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeClearSubDep1ByTenantIdx";
		ICFBamClearSubDep1Obj obj;
		List<ICFBamClearSubDep1Obj> arrayList = readCachedClearSubDep1ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep1ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeClearSubDep1ByClearDepIdx";
		ICFBamClearSubDep1Obj obj;
		List<ICFBamClearSubDep1Obj> arrayList = readCachedClearSubDep1ByClearDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeClearSubDep1ByDefSchemaIdx";
		ICFBamClearSubDep1Obj obj;
		List<ICFBamClearSubDep1Obj> arrayList = readCachedClearSubDep1ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep1ByClearTopDepIdx( CFLibDbKeyHash256 ClearTopDepId )
	{
		final String S_ProcName = "deepDisposeClearSubDep1ByClearTopDepIdx";
		ICFBamClearSubDep1Obj obj;
		List<ICFBamClearSubDep1Obj> arrayList = readCachedClearSubDep1ByClearTopDepIdx( ClearTopDepId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep1Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep1ByUNameIdx( CFLibDbKeyHash256 ClearTopDepId,
		String Name )
	{
		ICFBamClearSubDep1Obj obj = readCachedClearSubDep1ByUNameIdx( ClearTopDepId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearSubDep1Obj updateClearSubDep1( ICFBamClearSubDep1Obj Obj ) {
		ICFBamClearSubDep1Obj obj = Obj;
		schema.getCFBamBackingStore().getTableClearSubDep1().updateClearSubDep1( null,
			Obj.getClearSubDep1Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getClearSubDep1TableObj().getClassCode() ) {
			obj = (ICFBamClearSubDep1Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteClearSubDep1( ICFBamClearSubDep1Obj Obj ) {
		ICFBamClearSubDep1Obj obj = Obj;
		schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1( null,
			obj.getClearSubDep1Rec() );
		Obj.forget();
	}

	@Override
	public void deleteClearSubDep1ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep1Obj obj = readClearSubDep1(Id);
		if( obj != null ) {
			ICFBamClearSubDep1EditObj editObj = (ICFBamClearSubDep1EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamClearSubDep1EditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeClearSubDep1ByIdIdx( Id );
	}

	@Override
	public void deleteClearSubDep1ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamClearSubDep1Obj> iter = dict.values().iterator();
			ICFBamClearSubDep1Obj obj;
			List<ICFBamClearSubDep1Obj> toForget = new LinkedList<ICFBamClearSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTenantIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByTenantIdx( null,
				TenantId );
		}
		deepDisposeClearSubDep1ByTenantIdx( TenantId );
	}

	@Override
	public void deleteClearSubDep1ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( indexByClearDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict = indexByClearDepIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByClearDepIdx( null,
				RelationId );
			Iterator<ICFBamClearSubDep1Obj> iter = dict.values().iterator();
			ICFBamClearSubDep1Obj obj;
			List<ICFBamClearSubDep1Obj> toForget = new LinkedList<ICFBamClearSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClearDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByClearDepIdx( null,
				RelationId );
		}
		deepDisposeClearSubDep1ByClearDepIdx( RelationId );
	}

	@Override
	public void deleteClearSubDep1ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamClearSubDep1Obj> iter = dict.values().iterator();
			ICFBamClearSubDep1Obj obj;
			List<ICFBamClearSubDep1Obj> toForget = new LinkedList<ICFBamClearSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByDefSchemaIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeClearSubDep1ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteClearSubDep1ByClearTopDepIdx( CFLibDbKeyHash256 ClearTopDepId )
	{
		ICFBamClearSubDep1ByClearTopDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByClearTopDepIdxKey();
		key.setRequiredClearTopDepId( ClearTopDepId );
		if( indexByClearTopDepIdx == null ) {
			indexByClearTopDepIdx = new HashMap< ICFBamClearSubDep1ByClearTopDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep1Obj > >();
		}
		if( indexByClearTopDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep1Obj> dict = indexByClearTopDepIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByClearTopDepIdx( null,
				ClearTopDepId );
			Iterator<ICFBamClearSubDep1Obj> iter = dict.values().iterator();
			ICFBamClearSubDep1Obj obj;
			List<ICFBamClearSubDep1Obj> toForget = new LinkedList<ICFBamClearSubDep1Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClearTopDepIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByClearTopDepIdx( null,
				ClearTopDepId );
		}
		deepDisposeClearSubDep1ByClearTopDepIdx( ClearTopDepId );
	}

	@Override
	public void deleteClearSubDep1ByUNameIdx( CFLibDbKeyHash256 ClearTopDepId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearSubDep1ByUNameIdxKey,
				ICFBamClearSubDep1Obj >();
		}
		ICFBamClearSubDep1ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep1().newByUNameIdxKey();
		key.setRequiredClearTopDepId( ClearTopDepId );
		key.setRequiredName( Name );
		ICFBamClearSubDep1Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByUNameIdx( null,
				ClearTopDepId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep1().deleteClearSubDep1ByUNameIdx( null,
				ClearTopDepId,
				Name );
		}
		deepDisposeClearSubDep1ByUNameIdx( ClearTopDepId,
				Name );
	}
}