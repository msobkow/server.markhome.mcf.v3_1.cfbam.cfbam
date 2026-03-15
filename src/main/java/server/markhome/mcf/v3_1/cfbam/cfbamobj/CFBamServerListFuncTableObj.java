// Description: Java 25 Table Object implementation for ServerListFunc.

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

public class CFBamServerListFuncTableObj
	implements ICFBamServerListFuncTableObj
{
	protected ICFBamSchemaObj schema;
	protected static int runtimeClassCode = ICFBamServerListFunc.CLASS_CODE;
	protected static final int backingClassCode = ICFBamServerListFunc.CLASS_CODE;
	private Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> members;
	private Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> allServerListFunc;
	private Map< ICFBamScopeByTenantIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > > indexByTenantIdx;
	private Map< ICFBamServerMethodByUNameIdxKey,
		ICFBamServerListFuncObj > indexByUNameIdx;
	private Map< ICFBamServerMethodByMethTableIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > > indexByMethTableIdx;
	private Map< ICFBamServerMethodByDefSchemaIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > > indexByDefSchemaIdx;
	private Map< ICFBamServerListFuncByRetTblIdxKey,
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > > indexByRetTblIdx;
	public static String TABLE_NAME = "ServerListFunc";
	public static String TABLE_DBNAME = "srvlfunc";

	public CFBamServerListFuncTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerListFuncObj>();
		allServerListFunc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByRetTblIdx = null;
	}

	public CFBamServerListFuncTableObj( ICFBamSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFBamServerListFuncObj>();
		allServerListFunc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByRetTblIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFBamServerListFuncTableObj.getRuntimeClassCode();
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
			throw new CFLibArgumentUnderflowException(CFBamServerListFuncTableObj.class, "setRuntimeClassCode", 1, "argNewClassCode", argNewClassCode, 1);
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
		allServerListFunc = null;
		indexByTenantIdx = null;
		indexByUNameIdx = null;
		indexByMethTableIdx = null;
		indexByDefSchemaIdx = null;
		indexByRetTblIdx = null;
		List<ICFBamServerListFuncObj> toForget = new LinkedList<ICFBamServerListFuncObj>();
		ICFBamServerListFuncObj cur = null;
		Iterator<ICFBamServerListFuncObj> iter = members.values().iterator();
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
	 *	CFBamServerListFuncObj.
	 */
	@Override
	public ICFBamServerListFuncObj newInstance() {
		ICFBamServerListFuncObj inst = new CFBamServerListFuncObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamServerListFuncObj.
	 */
	@Override
	public ICFBamServerListFuncEditObj newEditInstance( ICFBamServerListFuncObj orig ) {
		ICFBamServerListFuncEditObj edit = new CFBamServerListFuncEditObj( orig );
		return( edit );
	}

	@Override
	public ICFBamServerListFuncObj realiseServerListFunc( ICFBamServerListFuncObj Obj ) {
		ICFBamServerListFuncObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerListFuncObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFBamServerListFuncObj existingObj = members.get( pkey );
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
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					indexByTenantIdx.remove( keyTenantIdx );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.remove( keyUNameIdx );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					indexByMethTableIdx.remove( keyMethTableIdx );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					indexByDefSchemaIdx.remove( keyDefSchemaIdx );
				}
			}

			if( indexByRetTblIdx != null ) {
				ICFBamServerListFuncByRetTblIdxKey keyRetTblIdx =
					schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
				keyRetTblIdx.setOptionalRetTableId( keepObj.getOptionalRetTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapRetTblIdx = indexByRetTblIdx.get( keyRetTblIdx );
				if( mapRetTblIdx != null ) {
					mapRetTblIdx.remove( keepObj.getPKey() );
					if( mapRetTblIdx.size() <= 0 ) {
						indexByRetTblIdx.remove( keyRetTblIdx );
					}
				}
			}
			// Keep passing the new object because it's the one with the record
			// that the base table needs to copy to the existing object from
			// the cache.
			keepObj = (ICFBamServerListFuncObj)schema.getServerMethodTableObj().realiseServerMethod( Obj );

			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRetTblIdx != null ) {
				ICFBamServerListFuncByRetTblIdxKey keyRetTblIdx =
					schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
				keyRetTblIdx.setOptionalRetTableId( keepObj.getOptionalRetTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapRetTblIdx = indexByRetTblIdx.get( keyRetTblIdx );
				if( mapRetTblIdx != null ) {
					mapRetTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allServerListFunc != null ) {
				allServerListFunc.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj = (ICFBamServerListFuncObj)schema.getServerMethodTableObj().realiseServerMethod( keepObj );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allServerListFunc != null ) {
				allServerListFunc.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTenantIdx != null ) {
				ICFBamScopeByTenantIdxKey keyTenantIdx =
					schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
				keyTenantIdx.setRequiredTenantId( keepObj.getRequiredTenantId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapTenantIdx = indexByTenantIdx.get( keyTenantIdx );
				if( mapTenantIdx != null ) {
					mapTenantIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUNameIdx != null ) {
				ICFBamServerMethodByUNameIdxKey keyUNameIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
				keyUNameIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				keyUNameIdx.setRequiredName( keepObj.getRequiredName() );
				indexByUNameIdx.put( keyUNameIdx, keepObj );
			}

			if( indexByMethTableIdx != null ) {
				ICFBamServerMethodByMethTableIdxKey keyMethTableIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
				keyMethTableIdx.setRequiredTableId( keepObj.getRequiredTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapMethTableIdx = indexByMethTableIdx.get( keyMethTableIdx );
				if( mapMethTableIdx != null ) {
					mapMethTableIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByDefSchemaIdx != null ) {
				ICFBamServerMethodByDefSchemaIdxKey keyDefSchemaIdx =
					schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
				keyDefSchemaIdx.setOptionalDefSchemaId( keepObj.getOptionalDefSchemaId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapDefSchemaIdx = indexByDefSchemaIdx.get( keyDefSchemaIdx );
				if( mapDefSchemaIdx != null ) {
					mapDefSchemaIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByRetTblIdx != null ) {
				ICFBamServerListFuncByRetTblIdxKey keyRetTblIdx =
					schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
				keyRetTblIdx.setOptionalRetTableId( keepObj.getOptionalRetTableId() );
				Map<CFLibDbKeyHash256, ICFBamServerListFuncObj > mapRetTblIdx = indexByRetTblIdx.get( keyRetTblIdx );
				if( mapRetTblIdx != null ) {
					mapRetTblIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFBamServerListFuncObj createServerListFunc( ICFBamServerListFuncObj Obj ) {
		ICFBamServerListFuncObj obj = Obj;
		ICFBamServerListFunc rec = obj.getServerListFuncRec();
		schema.getCFBamBackingStore().getTableServerListFunc().createServerListFunc(
			null,
			rec );
		obj.copyRecToPKey();
		if( obj.getClassCode() == runtimeClassCode ) {
			obj = (ICFBamServerListFuncObj)(obj.realise());
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFBamServerListFuncObj readServerListFunc( CFLibDbKeyHash256 pkey ) {
		return( readServerListFunc( pkey, false ) );
	}

	@Override
	public ICFBamServerListFuncObj readServerListFunc( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFBamServerListFuncObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFBamServerListFunc readRec = schema.getCFBamBackingStore().getTableServerListFunc().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( readRec.getClassCode() );
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFBamServerListFuncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFBamServerListFuncObj readCachedServerListFunc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerListFuncObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeServerListFunc( ICFBamServerListFuncObj obj )
	{
		final String S_ProcName = "CFBamServerListFuncTableObj.reallyDeepDisposeServerListFunc() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFBamServerListFuncObj existing = readCachedServerListFunc( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFBamServerListFuncByRetTblIdxKey keyRetTblIdx = schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
		keyRetTblIdx.setOptionalRetTableId( existing.getOptionalRetTableId() );


		schema.getParamTableObj().deepDisposeParamByServerMethodIdx( existing.getRequiredId() );

		if( indexByRetTblIdx != null ) {
			if( indexByRetTblIdx.containsKey( keyRetTblIdx ) ) {
				indexByRetTblIdx.get( keyRetTblIdx ).remove( pkey );
				if( indexByRetTblIdx.get( keyRetTblIdx ).size() <= 0 ) {
					indexByRetTblIdx.remove( keyRetTblIdx );
				}
			}
		}


		schema.getServerMethodTableObj().reallyDeepDisposeServerMethod( obj );
	}
	@Override
	public void deepDisposeServerListFunc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerListFuncObj obj = readCachedServerListFunc( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFBamServerListFuncObj lockServerListFunc( CFLibDbKeyHash256 pkey ) {
		ICFBamServerListFuncObj locked = null;
		ICFBamServerListFunc lockRec = schema.getCFBamBackingStore().getTableServerListFunc().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( lockRec.getClassCode() );
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFBamServerListFuncObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockServerListFunc", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFBamServerListFuncObj> readAllServerListFunc() {
		return( readAllServerListFunc( false ) );
	}

	@Override
	public List<ICFBamServerListFuncObj> readAllServerListFunc( boolean forceRead ) {
		final String S_ProcName = "readAllServerListFunc";
		if( ( allServerListFunc == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> map = new HashMap<CFLibDbKeyHash256,ICFBamServerListFuncObj>();
			allServerListFunc = map;
			ICFBamServerListFunc[] recList = schema.getCFBamBackingStore().getTableServerListFunc().readAllDerived( null );
			ICFBamServerListFunc rec;
			ICFBamServerListFuncObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerListFuncObj realised = (ICFBamServerListFuncObj)obj.realise();
			}
		}
		int len = allServerListFunc.size();
		ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
		Iterator<ICFBamServerListFuncObj> valIter = allServerListFunc.values().iterator();
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
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
		List<ICFBamServerListFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerListFuncObj> readCachedAllServerListFunc() {
		final String S_ProcName = "readCachedAllServerListFunc";
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>();
		if( allServerListFunc != null ) {
			int len = allServerListFunc.size();
			ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
			Iterator<ICFBamServerListFuncObj> valIter = allServerListFunc.values().iterator();
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
		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
	public ICFBamServerListFuncObj readServerListFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		return( readServerListFuncByIdIdx( Id,
			false ) );
	}

	@Override
	public ICFBamServerListFuncObj readServerListFuncByIdIdx( CFLibDbKeyHash256 Id, boolean forceRead )
	{
		ICFBamServerListFuncObj obj = readServerListFunc( Id, forceRead );
		return( obj );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		return( readServerListFuncByTenantIdx( TenantId,
			false ) );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerListFuncByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( ( ! forceRead ) && indexByTenantIdx.containsKey( key ) ) {
			dict = indexByTenantIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerListFuncObj>();
			ICFBamScopeObj obj;
			ICFBamScope[] recList = schema.getCFBamBackingStore().getTableScope().readDerivedByTenantIdx( null,
				TenantId );
			ICFBamScope rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerListFuncObj realised = (ICFBamServerListFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTenantIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
		Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
		List<ICFBamServerListFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerListFuncObj readServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		return( readServerListFuncByUNameIdx( TableId,
			Name,
			false ) );
	}

	@Override
	public ICFBamServerListFuncObj readServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name, boolean forceRead )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerListFuncObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerListFuncObj obj = null;
		if( ( ! forceRead ) && indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
		}
		else {
			ICFBamServerMethod rec = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByUNameIdx( null,
				TableId,
				Name );
			if( rec != null ) {
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFBamServerListFuncObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		return( readServerListFuncByMethTableIdx( TableId,
			false ) );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerListFuncByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( ( ! forceRead ) && indexByMethTableIdx.containsKey( key ) ) {
			dict = indexByMethTableIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerListFuncObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByMethTableIdx( null,
				TableId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerListFuncObj realised = (ICFBamServerListFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByMethTableIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
		Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
		List<ICFBamServerListFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		return( readServerListFuncByDefSchemaIdx( DefSchemaId,
			false ) );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerListFuncByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( ( ! forceRead ) && indexByDefSchemaIdx.containsKey( key ) ) {
			dict = indexByDefSchemaIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerListFuncObj>();
			ICFBamServerMethodObj obj;
			ICFBamServerMethod[] recList = schema.getCFBamBackingStore().getTableServerMethod().readDerivedByDefSchemaIdx( null,
				DefSchemaId );
			ICFBamServerMethod rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerListFuncObj realised = (ICFBamServerListFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByDefSchemaIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
		Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
		List<ICFBamServerListFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		return( readServerListFuncByRetTblIdx( RetTableId,
			false ) );
	}

	@Override
	public List<ICFBamServerListFuncObj> readServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId,
		boolean forceRead )
	{
		final String S_ProcName = "readServerListFuncByRetTblIdx";
		ICFBamServerListFuncByRetTblIdxKey key = schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
		key.setOptionalRetTableId( RetTableId );
		Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
		if( indexByRetTblIdx == null ) {
			indexByRetTblIdx = new HashMap< ICFBamServerListFuncByRetTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( ( ! forceRead ) && indexByRetTblIdx.containsKey( key ) ) {
			dict = indexByRetTblIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFBamServerListFuncObj>();
			ICFBamServerListFuncObj obj;
			ICFBamServerListFunc[] recList = schema.getCFBamBackingStore().getTableServerListFunc().readDerivedByRetTblIdx( null,
				RetTableId );
			ICFBamServerListFunc rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = (ICFBamServerListFuncObj)schema.getScopeTableObj().constructByClassCode( rec.getClassCode() );
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFBamServerListFuncObj realised = (ICFBamServerListFuncObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByRetTblIdx.put( key, dict );
		}
		int len = dict.size();
		ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
		Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
		List<ICFBamServerListFuncObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFBamServerListFuncObj readCachedServerListFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerListFuncObj obj = null;
		obj = readCachedServerListFunc( Id );
		return( obj );
	}

	@Override
	public List<ICFBamServerListFuncObj> readCachedServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "readCachedServerListFuncByTenantIdx";
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>();
		if( indexByTenantIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
			if( indexByTenantIdx.containsKey( key ) ) {
				dict = indexByTenantIdx.get( key );
				int len = dict.size();
				ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
				Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerListFuncObj obj;
			Iterator<ICFBamServerListFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
	public ICFBamServerListFuncObj readCachedServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerListFuncObj obj = null;
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		if( indexByUNameIdx != null ) {
			if( indexByUNameIdx.containsKey( key ) ) {
				obj = indexByUNameIdx.get( key );
			}
			else {
				Iterator<ICFBamServerListFuncObj> valIter = members.values().iterator();
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
			Iterator<ICFBamServerListFuncObj> valIter = members.values().iterator();
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
	public List<ICFBamServerListFuncObj> readCachedServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "readCachedServerListFuncByMethTableIdx";
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>();
		if( indexByMethTableIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
			if( indexByMethTableIdx.containsKey( key ) ) {
				dict = indexByMethTableIdx.get( key );
				int len = dict.size();
				ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
				Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerListFuncObj obj;
			Iterator<ICFBamServerListFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
	public List<ICFBamServerListFuncObj> readCachedServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "readCachedServerListFuncByDefSchemaIdx";
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>();
		if( indexByDefSchemaIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
			if( indexByDefSchemaIdx.containsKey( key ) ) {
				dict = indexByDefSchemaIdx.get( key );
				int len = dict.size();
				ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
				Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerListFuncObj obj;
			Iterator<ICFBamServerListFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
	public List<ICFBamServerListFuncObj> readCachedServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		final String S_ProcName = "readCachedServerListFuncByRetTblIdx";
		ICFBamServerListFuncByRetTblIdxKey key = schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
		key.setOptionalRetTableId( RetTableId );
		ArrayList<ICFBamServerListFuncObj> arrayList = new ArrayList<ICFBamServerListFuncObj>();
		if( indexByRetTblIdx != null ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict;
			if( indexByRetTblIdx.containsKey( key ) ) {
				dict = indexByRetTblIdx.get( key );
				int len = dict.size();
				ICFBamServerListFuncObj arr[] = new ICFBamServerListFuncObj[len];
				Iterator<ICFBamServerListFuncObj> valIter = dict.values().iterator();
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
			ICFBamServerListFuncObj obj;
			Iterator<ICFBamServerListFuncObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFBamServerListFuncObj> cmp = new Comparator<ICFBamServerListFuncObj>() {
			@Override
			public int compare( ICFBamServerListFuncObj lhs, ICFBamServerListFuncObj rhs ) {
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
	public void deepDisposeServerListFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerListFuncObj obj = readCachedServerListFuncByIdIdx( Id );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		final String S_ProcName = "deepDisposeServerListFuncByTenantIdx";
		ICFBamServerListFuncObj obj;
		List<ICFBamServerListFuncObj> arrayList = readCachedServerListFuncByTenantIdx( TenantId );
		if( arrayList != null )  {
			Iterator<ICFBamServerListFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		ICFBamServerListFuncObj obj = readCachedServerListFuncByUNameIdx( TableId,
				Name );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		final String S_ProcName = "deepDisposeServerListFuncByMethTableIdx";
		ICFBamServerListFuncObj obj;
		List<ICFBamServerListFuncObj> arrayList = readCachedServerListFuncByMethTableIdx( TableId );
		if( arrayList != null )  {
			Iterator<ICFBamServerListFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		final String S_ProcName = "deepDisposeServerListFuncByDefSchemaIdx";
		ICFBamServerListFuncObj obj;
		List<ICFBamServerListFuncObj> arrayList = readCachedServerListFuncByDefSchemaIdx( DefSchemaId );
		if( arrayList != null )  {
			Iterator<ICFBamServerListFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		final String S_ProcName = "deepDisposeServerListFuncByRetTblIdx";
		ICFBamServerListFuncObj obj;
		List<ICFBamServerListFuncObj> arrayList = readCachedServerListFuncByRetTblIdx( RetTableId );
		if( arrayList != null )  {
			Iterator<ICFBamServerListFuncObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public ICFBamServerListFuncObj updateServerListFunc( ICFBamServerListFuncObj Obj ) {
		ICFBamServerListFuncObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerListFunc().updateServerListFunc( null,
			Obj.getServerListFuncRec() );
		if( Obj.getClassCode() == ((ICFBamSchemaObj)getSchema()).getServerListFuncTableObj().getClassCode() ) {
			obj = (ICFBamServerListFuncObj)Obj.realise();
		}
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteServerListFunc( ICFBamServerListFuncObj Obj ) {
		ICFBamServerListFuncObj obj = Obj;
		schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFunc( null,
			obj.getServerListFuncRec() );
		Obj.forget();
	}

	@Override
	public void deleteServerListFuncByIdIdx( CFLibDbKeyHash256 Id )
	{
		ICFBamServerListFuncObj obj = readServerListFunc(Id);
		if( obj != null ) {
			ICFBamServerListFuncEditObj editObj = (ICFBamServerListFuncEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFBamServerListFuncEditObj)obj.beginEdit();
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
		deepDisposeServerListFuncByIdIdx( Id );
	}

	@Override
	public void deleteServerListFuncByTenantIdx( CFLibDbKeyHash256 TenantId )
	{
		ICFBamScopeByTenantIdxKey key = schema.getCFBamBackingStore().getFactoryScope().newByTenantIdxKey();
		key.setRequiredTenantId( TenantId );
		if( indexByTenantIdx == null ) {
			indexByTenantIdx = new HashMap< ICFBamScopeByTenantIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( indexByTenantIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict = indexByTenantIdx.get( key );
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByTenantIdx( null,
				TenantId );
			Iterator<ICFBamServerListFuncObj> iter = dict.values().iterator();
			ICFBamServerListFuncObj obj;
			List<ICFBamServerListFuncObj> toForget = new LinkedList<ICFBamServerListFuncObj>();
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
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByTenantIdx( null,
				TenantId );
		}
		deepDisposeServerListFuncByTenantIdx( TenantId );
	}

	@Override
	public void deleteServerListFuncByUNameIdx( CFLibDbKeyHash256 TableId,
		String Name )
	{
		if( indexByUNameIdx == null ) {
			indexByUNameIdx = new HashMap< ICFBamServerMethodByUNameIdxKey,
				ICFBamServerListFuncObj >();
		}
		ICFBamServerMethodByUNameIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByUNameIdxKey();
		key.setRequiredTableId( TableId );
		key.setRequiredName( Name );
		ICFBamServerListFuncObj obj = null;
		if( indexByUNameIdx.containsKey( key ) ) {
			obj = indexByUNameIdx.get( key );
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByUNameIdx( null,
				TableId,
				Name );
			obj.forget();
		}
		else {
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByUNameIdx( null,
				TableId,
				Name );
		}
		deepDisposeServerListFuncByUNameIdx( TableId,
				Name );
	}

	@Override
	public void deleteServerListFuncByMethTableIdx( CFLibDbKeyHash256 TableId )
	{
		ICFBamServerMethodByMethTableIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByMethTableIdxKey();
		key.setRequiredTableId( TableId );
		if( indexByMethTableIdx == null ) {
			indexByMethTableIdx = new HashMap< ICFBamServerMethodByMethTableIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( indexByMethTableIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict = indexByMethTableIdx.get( key );
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByMethTableIdx( null,
				TableId );
			Iterator<ICFBamServerListFuncObj> iter = dict.values().iterator();
			ICFBamServerListFuncObj obj;
			List<ICFBamServerListFuncObj> toForget = new LinkedList<ICFBamServerListFuncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByMethTableIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByMethTableIdx( null,
				TableId );
		}
		deepDisposeServerListFuncByMethTableIdx( TableId );
	}

	@Override
	public void deleteServerListFuncByDefSchemaIdx( CFLibDbKeyHash256 DefSchemaId )
	{
		ICFBamServerMethodByDefSchemaIdxKey key = schema.getCFBamBackingStore().getFactoryServerMethod().newByDefSchemaIdxKey();
		key.setOptionalDefSchemaId( DefSchemaId );
		if( indexByDefSchemaIdx == null ) {
			indexByDefSchemaIdx = new HashMap< ICFBamServerMethodByDefSchemaIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( indexByDefSchemaIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict = indexByDefSchemaIdx.get( key );
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByDefSchemaIdx( null,
				DefSchemaId );
			Iterator<ICFBamServerListFuncObj> iter = dict.values().iterator();
			ICFBamServerListFuncObj obj;
			List<ICFBamServerListFuncObj> toForget = new LinkedList<ICFBamServerListFuncObj>();
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
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByDefSchemaIdx( null,
				DefSchemaId );
		}
		deepDisposeServerListFuncByDefSchemaIdx( DefSchemaId );
	}

	@Override
	public void deleteServerListFuncByRetTblIdx( CFLibDbKeyHash256 RetTableId )
	{
		ICFBamServerListFuncByRetTblIdxKey key = schema.getCFBamBackingStore().getFactoryServerListFunc().newByRetTblIdxKey();
		key.setOptionalRetTableId( RetTableId );
		if( indexByRetTblIdx == null ) {
			indexByRetTblIdx = new HashMap< ICFBamServerListFuncByRetTblIdxKey,
				Map< CFLibDbKeyHash256, ICFBamServerListFuncObj > >();
		}
		if( indexByRetTblIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFBamServerListFuncObj> dict = indexByRetTblIdx.get( key );
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByRetTblIdx( null,
				RetTableId );
			Iterator<ICFBamServerListFuncObj> iter = dict.values().iterator();
			ICFBamServerListFuncObj obj;
			List<ICFBamServerListFuncObj> toForget = new LinkedList<ICFBamServerListFuncObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByRetTblIdx.remove( key );
		}
		else {
			schema.getCFBamBackingStore().getTableServerListFunc().deleteServerListFuncByRetTblIdx( null,
				RetTableId );
		}
		deepDisposeServerListFuncByRetTblIdx( RetTableId );
	}
}