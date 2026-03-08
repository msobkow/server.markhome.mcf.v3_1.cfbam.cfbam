// Description: Java 25 Table Object implementation for ClearSubDep2.

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

public class CFBamClearSubDep2TableObj
	implements ICFBamClearSubDep2TableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamClearSubDep2.CLASS_CODE;
	protected static final int backingClassCode = ICFBamClearSubDep2.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> members;
	private Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> allClearSubDep2;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > > indexByTenantIdx;
	private Map< ICFBamClearDepByClearDepIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > > indexByClearDepIdx;
	private Map< ICFBamClearDepByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > > indexByDefSchemaIdx;
	private Map< ICFBamClearSubDep2ByClearSubDep1IdxKey,
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > > indexByClearSubDep1Idx;
	private Map< ICFBamClearSubDep2ByUNameIdxKey,
		ICFBamClearSubDep2Obj > indexByUNameIdx;
	public static String TABLE_NAME = "ClearSubDep2";
	public static String TABLE_DBNAME = "clrsubdep2";

	public CFBamClearSubDep2TableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep2Obj>();
		allClearSubDep2 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearSubDep1Idx = null;
		indexByUNameIdx = null;
	}

	public CFBamClearSubDep2TableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep2Obj>();
		allClearSubDep2 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearSubDep1Idx = null;
		indexByUNameIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamClearSubDep2TableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamClearSubDep2TableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allClearSubDep2 = null;
		indexByTenantIdx = null;
		indexByClearDepIdx = null;
		indexByDefSchemaIdx = null;
		indexByClearSubDep1Idx = null;
		indexByUNameIdx = null;
		List<ICFBamClearSubDep2Obj> toForget = new LinkedList<ICFBamClearSubDep2Obj>();
		ICFBamClearSubDep2Obj cur = null;
		Iterator<ICFBamClearSubDep2Obj> iter = members.values().iterator();
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
	 *	CFBamClearSubDep2Obj.
	 */
	@Override
	public ICFBamClearSubDep2Obj newInstance() {
		ICFBamClearSubDep2Obj inst = new CFBamClearSubDep2Obj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamClearSubDep2Obj.
	 */
	@Override
	public ICFBamClearSubDep2EditObj newEditInstance( ICFBamClearSubDep2Obj orig ) {
		ICFBamClearSubDep2EditObj edit = new CFBamClearSubDep2EditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamClearSubDep2Obj realiseClearSubDep2( ICFBamClearSubDep2Obj Obj ) {
		ICFBamClearSubDep2Obj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearSubDep2Obj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamClearSubDep2Obj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					indexByClearDepIdx.remove( keyClearDepIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByClearSubDep1Idx != null ) {
				ICFBamClearSubDep2ByClearSubDep1IdxKey keyClearSubDep1Idx =
					schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
				keyClearSubDep1Idx.setRequiredClearSubDep1Id( keepObj.getRequiredClearSubDep1Id() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapClearSubDep1Idx = indexByClearSubDep1Idx.get( keyClearSubDep1Idx );
				if( mapClearSubDep1Idx != null ) {
					mapClearSubDep1Idx.remove( keepObj.getPKey() );
					if( mapClearSubDep1Idx.size() <= 0 ) {
						indexByClearSubDep1Idx.remove( keyClearSubDep1Idx );
					}
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep2ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearSubDep1Id( keepObj.getRequiredClearSubDep1Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamClearSubDep2Obj)schema.getClearDepTableObj().realiseClearDep( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearSubDep1Idx != null ) {
				ICFBamClearSubDep2ByClearSubDep1IdxKey keyClearSubDep1Idx =
					schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
				keyClearSubDep1Idx.setRequiredClearSubDep1Id( keepObj.getRequiredClearSubDep1Id() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapClearSubDep1Idx = indexByClearSubDep1Idx.get( keyClearSubDep1Idx );
				if( mapClearSubDep1Idx != null ) {
					mapClearSubDep1Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep2ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearSubDep1Id( keepObj.getRequiredClearSubDep1Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( allClearSubDep2 != null ) {
				allClearSubDep2.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamClearSubDep2Obj)schema.getClearDepTableObj().realiseClearDep( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allClearSubDep2 != null ) {
				allClearSubDep2.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearDepIdx != null ) {
				ICFBamClearDepByClearDepIdxKey keyClearDepIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
				keyClearDepIdx.setRequiredRelationId( keepObj.getRequiredRelationId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapClearDepIdx = indexByClearDepIdx.get( keyClearDepIdx );
				if( mapClearDepIdx != null ) {
					mapClearDepIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamClearDepByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByClearSubDep1Idx != null ) {
				ICFBamClearSubDep2ByClearSubDep1IdxKey keyClearSubDep1Idx =
					schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
				keyClearSubDep1Idx.setRequiredClearSubDep1Id( keepObj.getRequiredClearSubDep1Id() );
				Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj > mapClearSubDep1Idx = indexByClearSubDep1Idx.get( keyClearSubDep1Idx );
				if( mapClearSubDep1Idx != null ) {
					mapClearSubDep1Idx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamClearSubDep2ByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
				keyUNameIdx.setRequiredClearSubDep1Id( keepObj.getRequiredClearSubDep1Id() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamClearSubDep2Obj createClearSubDep2( ICFBamClearSubDep2Obj Obj ) {
		ICFBamClearSubDep2Obj obj = Obj;
		ICFBamClearSubDep2 rec = obj.getClearSubDep2Rec();
		schema.getCFBamBackingStore().getTableClearSubDep2().createClearSubDep2(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamClearSubDep2Obj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamClearSubDep2Obj readClearSubDep2( CFLibDbKeyHash256 pkey ) {
		return( readClearSubDep2( pkey, false ) );
	}

	@Override
	public ICFBamClearSubDep2Obj readClearSubDep2( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamClearSubDep2Obj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamClearSubDep2 readRec = schema.getCFBamBackingStore().getTableClearSubDep2().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamClearSubDep2Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearSubDep2Obj readCachedClearSubDep2( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep2Obj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeClearSubDep2( ICFBamClearSubDep2Obj obj )
	{
		final String S_ProcName = "CFBamClearSubDep2TableObj.reallyDeepDisposeClearSubDep2() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamClearSubDep2Obj existing = readCachedClearSubDep2( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamClearSubDep2ByClearSubDep1IdxKey keyClearSubDep1Idx = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
		keyClearSubDep1Idx.setRequiredClearSubDep1Id( existing.getRequiredClearSubDep1Id() );

		ICFBamClearSubDep2ByUNameIdxKey keyUNameIdx = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
		keyUNameIdx.setRequiredClearSubDep1Id( existing.getRequiredClearSubDep1Id() );
		keyUNameIdx.setRequiredName( existing.getRequiredName() );


		schema.getClearSubDep3TableObj().deepDisposeClearSubDep3ByClearSubDep2Idx( existing.getRequiredId() );

		if( indexByClearSubDep1Idx != null ) {
			if( indexByClearSubDep1Idx.containsKey( keyClearSubDep1Idx ) ) {
				indexByClearSubDep1Idx.get( keyClearSubDep1Idx ).remove( pkey );
				if( indexByClearSubDep1Idx.get( keyClearSubDep1Idx ).size() <= 0 ) {
					indexByClearSubDep1Idx.remove( keyClearSubDep1Idx );
				}
			}
		}

		if( indexByUNameIdx != null ) {
			indexByUNameIdx.remove( keyUNameIdx );
		}


		schema.getClearDepTableObj().reallyDeepDisposeClearDep( obj );
	}
	@Override
	public void deepDisposeClearSubDep2( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep2Obj obj = readCachedClearSubDep2( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearSubDep2Obj lockClearSubDep2( CFLibDbKeyHash256 pkey ) {
		ICFBamClearSubDep2Obj locked = null;
		ICFBamClearSubDep2 lockRec = schema.getCFBamBackingStore().getTableClearSubDep2().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamClearSubDep2Obj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockClearSubDep2", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readAllClearSubDep2() {
		return( readAllClearSubDep2( false ) );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readAllClearSubDep2( boolean forceRead ) {
		final String S_ProcName = "readAllClearSubDep2";
		if( ( allClearSubDep2 == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> map = new HashMap<CFLibDbKeyHash256,ICFBamClearSubDep2Obj>();
			allClearSubDep2 = map;
			ICFBamClearSubDep2[] recList = schema.getCFBamBackingStore().getTableClearSubDep2().readAllDerived( null );
			ICFBamClearSubDep2 rec;
			ICFBamClearSubDep2Obj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep2Obj realised = (ICFBamClearSubDep2Obj)obj.realise();
			}
		}
		int len = allClearSubDep2.size();
		ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
		Iterator<ICFBamClearSubDep2Obj> valIter = allClearSubDep2.values().iterator();
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
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
		List<ICFBamClearSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readCachedAllClearSubDep2() {
		final String S_ProcName = "readCachedAllClearSubDep2";
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>();
		if( allClearSubDep2 != null ) {
			int len = allClearSubDep2.size();
			ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
			Iterator<ICFBamClearSubDep2Obj> valIter = allClearSubDep2.values().iterator();
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
		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
	public ICFBamClearSubDep2Obj readClearSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readClearSubDep2ByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamClearSubDep2Obj readClearSubDep2ByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamClearSubDep2Obj obj = readClearSubDep2( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readClearSubDep2ByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep2ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep2Obj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep2Obj realised = (ICFBamClearSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
		Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
		List<ICFBamClearSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		return( readClearSubDep2ByClearDepIdx( RelationId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByClearDepIdx( CFLibDbKeyHash256 RelationId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep2ByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByClearDepIdx.containsKey( key ) ) {
			dict = indexByClearDepIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep2Obj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByClearDepIdx( null,
				RelationId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep2Obj realised = (ICFBamClearSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearDepIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
		Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
		List<ICFBamClearSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readClearSubDep2ByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep2ByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep2Obj>();
			ICFBamClearDepObj obj;
			ICFBamClearDep[] recList = schema.getCFBamBackingStore().getTableClearDep().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamClearDep rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep2Obj realised = (ICFBamClearSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
		Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
		List<ICFBamClearSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByClearSubDep1Idx( CFLibDbKeyHash256 ClearSubDep1Id )
	{
		return( readClearSubDep2ByClearSubDep1Idx( ClearSubDep1Id,
			false ) );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readClearSubDep2ByClearSubDep1Idx( CFLibDbKeyHash256 ClearSubDep1Id,
		boolean forceRead )
	{
		final String S_ProcName = "readClearSubDep2ByClearSubDep1Idx";
		ICFBamClearSubDep2ByClearSubDep1IdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
		key.setRequiredClearSubDep1Id( ClearSubDep1Id );
		Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
		if( indexByClearSubDep1Idx == null ) {
			indexByClearSubDep1Idx = new HashMap< ICFBamClearSubDep2ByClearSubDep1IdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( ( ! forceRead ) && indexByClearSubDep1Idx.containsKey( key ) ) {
			dict = indexByClearSubDep1Idx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamClearSubDep2Obj>();
			ICFBamClearSubDep2Obj obj;
			ICFBamClearSubDep2[] recList = schema.getCFBamBackingStore().getTableClearSubDep2().readDerivedByClearSubDep1Idx( null,
				ClearSubDep1Id );
			ICFBamClearSubDep2 rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamClearSubDep2Obj realised = (ICFBamClearSubDep2Obj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByClearSubDep1Idx.put( key, dict );
		}
		int len = dict.size();
		ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
		Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
		List<ICFBamClearSubDep2Obj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamClearSubDep2Obj readClearSubDep2ByUNameIdx( CFLibDbKeyHash256 ClearSubDep1Id,
		String Name )
	{
		return( readClearSubDep2ByUNameIdx( ClearSubDep1Id,
			Name,
			false ) );
	}

	@Override
	public ICFBamClearSubDep2Obj readClearSubDep2ByUNameIdx( CFLibDbKeyHash256 ClearSubDep1Id,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearSubDep2ByUNameIdxKey,
				ICFBamClearSubDep2Obj >();
		}
		ICFBamClearSubDep2ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
		key.setRequiredClearSubDep1Id( ClearSubDep1Id );
		key.setRequiredName( Name );
		ICFBamClearSubDep2Obj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamClearSubDep2 rec = schema.getCFBamBackingStore().getTableClearSubDep2().readDerivedByUNameIdx( null,
				ClearSubDep1Id,
				Name );
			if( rec != null ) {
				obj = (ICFBamClearSubDep2Obj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamClearSubDep2Obj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamClearSubDep2Obj readCachedClearSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep2Obj obj = null;
		obj = readCachedClearSubDep2( Id );
		return( obj );
	}

	@Override
	public List<ICFBamClearSubDep2Obj> readCachedClearSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedClearSubDep2ByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
				Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep2Obj obj;
			Iterator<ICFBamClearSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
	public List<ICFBamClearSubDep2Obj> readCachedClearSubDep2ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "readCachedClearSubDep2ByClearDepIdx";
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>();
		if( indexByClearDepIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
			if( indexByClearDepIdx.containsKey( key ) ) {
				dict = indexByClearDepIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
				Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep2Obj obj;
			Iterator<ICFBamClearSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
	public List<ICFBamClearSubDep2Obj> readCachedClearSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedClearSubDep2ByDefSchemaIdx";
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
				Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep2Obj obj;
			Iterator<ICFBamClearSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
	public List<ICFBamClearSubDep2Obj> readCachedClearSubDep2ByClearSubDep1Idx( CFLibDbKeyHash256 ClearSubDep1Id )
	{
		final String S_ProcName = "readCachedClearSubDep2ByClearSubDep1Idx";
		ICFBamClearSubDep2ByClearSubDep1IdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
		key.setRequiredClearSubDep1Id( ClearSubDep1Id );
		ArrayList<ICFBamClearSubDep2Obj> arrayList = new ArrayList<ICFBamClearSubDep2Obj>();
		if( indexByClearSubDep1Idx != null ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict;
			if( indexByClearSubDep1Idx.containsKey( key ) ) {
				dict = indexByClearSubDep1Idx.get( key );
				int len = dict.size();
				ICFBamClearSubDep2Obj arr[] = new ICFBamClearSubDep2Obj[len];
				Iterator<ICFBamClearSubDep2Obj> valIter = dict.values().iterator();
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
			ICFBamClearSubDep2Obj obj;
			Iterator<ICFBamClearSubDep2Obj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamClearSubDep2Obj> cmp = new Comparator<ICFBamClearSubDep2Obj>() {
			@Override
			public int compare( ICFBamClearSubDep2Obj lhs, ICFBamClearSubDep2Obj rhs ) {
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
	public ICFBamClearSubDep2Obj readCachedClearSubDep2ByUNameIdx( CFLibDbKeyHash256 ClearSubDep1Id,
		String Name )
	{
		ICFBamClearSubDep2Obj obj = null;
		ICFBamClearSubDep2ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
		key.setRequiredClearSubDep1Id( ClearSubDep1Id );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamClearSubDep2Obj> valIter = members.values().iterator();
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
			Iterator<ICFBamClearSubDep2Obj> valIter = members.values().iterator();
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
	public void deepDisposeClearSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep2Obj obj = readCachedClearSubDep2ByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeClearSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeClearSubDep2ByTenantIdx";
		ICFBamClearSubDep2Obj obj;
		List<ICFBamClearSubDep2Obj> arrayList = readCachedClearSubDep2ByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep2ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		final String S_ProcName = "deepDisposeClearSubDep2ByClearDepIdx";
		ICFBamClearSubDep2Obj obj;
		List<ICFBamClearSubDep2Obj> arrayList = readCachedClearSubDep2ByClearDepIdx( RelationId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeClearSubDep2ByDefSchemaIdx";
		ICFBamClearSubDep2Obj obj;
		List<ICFBamClearSubDep2Obj> arrayList = readCachedClearSubDep2ByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep2ByClearSubDep1Idx( CFLibDbKeyHash256 ClearSubDep1Id )
	{
		final String S_ProcName = "deepDisposeClearSubDep2ByClearSubDep1Idx";
		ICFBamClearSubDep2Obj obj;
		List<ICFBamClearSubDep2Obj> arrayList = readCachedClearSubDep2ByClearSubDep1Idx( ClearSubDep1Id );
		if( arrayList != null )  {
			Iterator<ICFBamClearSubDep2Obj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeClearSubDep2ByUNameIdx( CFLibDbKeyHash256 ClearSubDep1Id,
		String Name )
	{
		ICFBamClearSubDep2Obj obj = readCachedClearSubDep2ByUNameIdx( ClearSubDep1Id,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamClearSubDep2Obj updateClearSubDep2( ICFBamClearSubDep2Obj Obj ) {
		ICFBamClearSubDep2Obj obj = Obj;
		schema.getCFBamBackingStore().getTableClearSubDep2().updateClearSubDep2( null,
			Obj.getClearSubDep2Rec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getClearSubDep2TableObj().getClassCode() ) {
			obj = (ICFBamClearSubDep2Obj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteClearSubDep2( ICFBamClearSubDep2Obj Obj ) {
		ICFBamClearSubDep2Obj obj = Obj;
		schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2( null,
			obj.getClearSubDep2Rec() );
		Obj.forget();
	}

	@Override
	public void deleteClearSubDep2ByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamClearSubDep2Obj obj = readClearSubDep2(Id);
		if( obj != null ) {
			ICFBamClearSubDep2EditObj editObj = (ICFBamClearSubDep2EditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamClearSubDep2EditObj)obj.beginEdit();
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
		deepDisposeClearSubDep2ByIdIdx( Id );
	}

	@Override
	public void deleteClearSubDep2ByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByTenantIdx( null,
				TenantId );
			Iterator<ICFBamClearSubDep2Obj> iter = dict.values().iterator();
			ICFBamClearSubDep2Obj obj;
			List<ICFBamClearSubDep2Obj> toForget = new LinkedList<ICFBamClearSubDep2Obj>();
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
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByTenantIdx( null,
				TenantId );
		}
		deepDisposeClearSubDep2ByTenantIdx( TenantId );
	}

	@Override
	public void deleteClearSubDep2ByClearDepIdx( CFLibDbKeyHash256 RelationId )
	{
		ICFBamClearDepByClearDepIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByClearDepIdxKey();
		key.setRequiredRelationId( RelationId );
		if( indexByClearDepIdx == null ) {
			indexByClearDepIdx = new HashMap< ICFBamClearDepByClearDepIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( indexByClearDepIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict = indexByClearDepIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByClearDepIdx( null,
				RelationId );
			Iterator<ICFBamClearSubDep2Obj> iter = dict.values().iterator();
			ICFBamClearSubDep2Obj obj;
			List<ICFBamClearSubDep2Obj> toForget = new LinkedList<ICFBamClearSubDep2Obj>();
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
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByClearDepIdx( null,
				RelationId );
		}
		deepDisposeClearSubDep2ByClearDepIdx( RelationId );
	}

	@Override
	public void deleteClearSubDep2ByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamClearDepByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryClearDep().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamClearDepByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamClearSubDep2Obj> iter = dict.values().iterator();
			ICFBamClearSubDep2Obj obj;
			List<ICFBamClearSubDep2Obj> toForget = new LinkedList<ICFBamClearSubDep2Obj>();
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
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeClearSubDep2ByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteClearSubDep2ByClearSubDep1Idx( CFLibDbKeyHash256 ClearSubDep1Id )
	{
		ICFBamClearSubDep2ByClearSubDep1IdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByClearSubDep1IdxKey();
		key.setRequiredClearSubDep1Id( ClearSubDep1Id );
		if( indexByClearSubDep1Idx == null ) {
			indexByClearSubDep1Idx = new HashMap< ICFBamClearSubDep2ByClearSubDep1IdxKey,
				Map< CFLibDbKeyHash256, ICFBamClearSubDep2Obj > >();
		}
		if( indexByClearSubDep1Idx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamClearSubDep2Obj> dict = indexByClearSubDep1Idx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByClearSubDep1Idx( null,
				ClearSubDep1Id );
			Iterator<ICFBamClearSubDep2Obj> iter = dict.values().iterator();
			ICFBamClearSubDep2Obj obj;
			List<ICFBamClearSubDep2Obj> toForget = new LinkedList<ICFBamClearSubDep2Obj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByClearSubDep1Idx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByClearSubDep1Idx( null,
				ClearSubDep1Id );
		}
		deepDisposeClearSubDep2ByClearSubDep1Idx( ClearSubDep1Id );
	}

	@Override
	public void deleteClearSubDep2ByUNameIdx( CFLibDbKeyHash256 ClearSubDep1Id,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamClearSubDep2ByUNameIdxKey,
				ICFBamClearSubDep2Obj >();
		}
		ICFBamClearSubDep2ByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryClearSubDep2().newByUNameIdxKey();
		key.setRequiredClearSubDep1Id( ClearSubDep1Id );
		key.setRequiredName( Name );
		ICFBamClearSubDep2Obj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByUNameIdx( null,
				ClearSubDep1Id,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableClearSubDep2().deleteClearSubDep2ByUNameIdx( null,
				ClearSubDep1Id,
				Name );
		}
		deepDisposeClearSubDep2ByUNameIdx( ClearSubDep1Id,
				Name );
	}
}